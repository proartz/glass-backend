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

    public enum OperationStatus {DISABLED, READY_FOR_REALISATION, IN_REALISATION, DONE};
    public enum OrderStatus {RECEIVED, IN_REALISATION, READY, DELIVERED, PAID};

    public StatusService(StageOneOperations stageOneOperations, StageTwoOperations stageTwoOperations) {
        this.stageOneOperations = stageOneOperations;
        this.stageTwoOperations = stageTwoOperations;
    }

    public Order changeOrderStatuses(Operation operation, OperationStatus newStatus) {
        Item item = operation.getItem();
        Order order = item.getOrder();

        operation.setStatus(newStatus.name());

        if(newStatus == OperationStatus.IN_REALISATION) {
            item.setStatus(OperationStatus.IN_REALISATION.name());
            order.setStatus(OrderStatus.IN_REALISATION.name());
            disableOtherOperationsInStage(item, operation);
        } else if (newStatus == OperationStatus.DONE) {
            if(countNotDoneOperations(item) == 0) {
                item.setStatus(OperationStatus.DONE.name());
                order.setStatus(OrderStatus.READY.name());

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
                if(stageOneOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(OperationStatus.DONE.name())) {
                    otherOperation.setStatus(OperationStatus.DISABLED.name());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(OperationStatus.DONE.name())) {
                    otherOperation.setStatus(OperationStatus.DISABLED.name());
                }
            }
        }
    }

    public int countNotDoneOperations(Item item) {
        int counter = 0;
        for(Operation operation : item.getOperations()) {
            if(!operation.getStatus().equals(OperationStatus.DONE.name())) {
                counter++;
            }
        }
        return counter;
    }

    private int countStageOneOperations(Item item) {
        int counter = 0;
        for(Operation operation : item.getOperations()) {
            if(stageOneOperations.getOperations().contains(operation.getName()) && !operation.getStatus().equals(OperationStatus.DONE.name())) {
                counter++;
            }
        }
        return counter;
    }

    private void enableOtherOperationsInStage(Item item, Operation operation) {
        if(stageOneOperations.getOperations().contains(operation.getName())) {
            for(Operation otherOperation : item.getOperations()) {
                if(stageOneOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(OperationStatus.DISABLED.name())) {
                    otherOperation.setStatus(OperationStatus.READY_FOR_REALISATION.name());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.getOperations().contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(OperationStatus.DISABLED.name())) {
                    otherOperation.setStatus(OperationStatus.READY_FOR_REALISATION.name());
                }
            }
        }
    }

    private void enableStageTwoOperations(Item item) {
        for(Operation otherOperation : item.getOperations()) {
            if(stageTwoOperations.getOperations().contains(otherOperation.getName()))
                otherOperation.setStatus(OperationStatus.READY_FOR_REALISATION.name());
        }
    }

    public void prepareStatuses(Order order) {
        order.setStatus(OrderStatus.RECEIVED.name());
        Boolean stageOne = false;
        for(Item item : order.getItems()) {
            item.setStatus(OperationStatus.READY_FOR_REALISATION.name());
            for(Operation operation : item.getOperations()) {
                if(stageOneOperations.getOperations().contains(operation.getName())) {
                    operation.setStatus(OperationStatus.READY_FOR_REALISATION.name());
                    stageOne = true;
                }
            }
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
        operation.setStatus(OperationStatus.DISABLED.name());
    }
}
