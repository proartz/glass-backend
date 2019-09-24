package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oncode.glass.config.StageOneOperations;
import pl.oncode.glass.config.StageTwoOperations;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;

@Service(value = "statusService")
public class StatusService {

    Logger logger = LoggerFactory.getLogger(StatusService.class);

    StageOneOperations stageOneOperations;
    StageTwoOperations stageTwoOperations;

    public enum OperationStatus {ZABLOKOWANE, GOTOWE_DO_REALIZACJI , W_REALIZACJI, ZROBIONE};
    public enum OrderStatus {PRZYJĘTO, W_REALIZACJI, GOTOWE, WYDANE, ROZLICZONE};

    public StatusService(StageOneOperations stageOneOperations, StageTwoOperations stageTwoOperations) {
        this.stageOneOperations = stageOneOperations;
        this.stageTwoOperations = stageTwoOperations;
    }

    public Order changeOrderStatuses(Operation operation, OperationStatus newStatus) {
        Item item = operation.getItem();
        Order order = item.getOrder();

        operation.setStatus(newStatus.name());

        if(newStatus == OperationStatus.W_REALIZACJI) {
            item.setStatus(OperationStatus.W_REALIZACJI.name());
            order.setStatus(OrderStatus.W_REALIZACJI.name());
            disableOtherOperationsInStage(item, operation);
        } else if (newStatus == OperationStatus.ZROBIONE) {
            if(countNotDoneOperations(item) == 0) {
                item.setStatus(OperationStatus.ZROBIONE.name());
                order.setStatus(OrderStatus.GOTOWE.name());

                return order;
            }
            if(stageOneOperations.getOperations().contains(operation.getName())) {
                int stageOneCounter = countStageOneOperations(item);
                if(stageOneCounter > 0) {
                    enableOtherOperationsInStage(item, operation);
                } else {
                    enableStageTwoOperations(item);
                }
            } else {
                enableOtherOperationsInStage(item, operation);
            }

        }

        return order;
    }

    private void disableOtherOperationsInStage(Item item, Operation operation) {
        if(stageOneOperations.getOperations().contains(operation.getName())) {
            for(Operation otherOperation : item.getOperations()) {
                if(stageOneOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(OperationStatus.ZROBIONE.name())) {
                    otherOperation.setStatus(OperationStatus.ZABLOKOWANE.name());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(OperationStatus.ZROBIONE.name())) {
                    otherOperation.setStatus(OperationStatus.ZABLOKOWANE.name());
                }
            }
        }
    }

    public int countNotDoneOperations(Item item) {
        int counter = 0;
        for(Operation operation : item.getOperations()) {
            if(!operation.getStatus().equals(OperationStatus.ZROBIONE.name())) {
                counter++;
            }
        }
        return counter;
    }

    private int countStageOneOperations(Item item) {
        int counter = 0;
        for(Operation operation : item.getOperations()) {
            if(stageOneOperations.getOperations().contains(operation.getName()) && !operation.getStatus().equals(OperationStatus.ZROBIONE.name())) {
                counter++;
            }
        }
        return counter;
    }

    private void enableOtherOperationsInStage(Item item, Operation operation) {
        if(stageOneOperations.getOperations().contains(operation.getName())) {
            for(Operation otherOperation : item.getOperations()) {
                if(stageOneOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(OperationStatus.ZABLOKOWANE.name())) {
                    otherOperation.setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(OperationStatus.ZABLOKOWANE.name())) {
                    otherOperation.setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
                }
            }
        }
    }

    private void enableStageTwoOperations(Item item) {
        for(Operation otherOperation : item.getOperations()) {
            if(stageTwoOperations.getOperations().contains(otherOperation.getName()))
                otherOperation.setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
        }
    }

    public void prepareStatuses(Order order) {
        order.setStatus(OrderStatus.PRZYJĘTO.name());
        for(Item item : order.getItems()) {
            prepareItemStatuses(item);
        }
    }

    public void prepareItemStatuses(Item item) {
        Boolean stageOne = false;
        item.setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
        for(Operation operation : item.getOperations()) {
            if(stageOneOperations.getOperations().contains(operation.getName())) {
                stageOne = true;
            }
            operation.setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
        }
        if(stageOne) {
            disableStageTwoOperations(item);
        }
    }

    public void disableStageTwoOperations(Item item) {
        for(Operation operation : item.getOperations()) {
            if(stageTwoOperations.getOperations().contains(operation.getName())) {
                disableOperation(operation);
            }
        }
    }

    public void disableOperation(Operation operation) {
        operation.setStatus(OperationStatus.ZABLOKOWANE.name());
    }
}
