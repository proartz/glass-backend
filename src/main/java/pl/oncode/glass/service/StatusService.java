package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;

import java.util.HashSet;
import java.util.Set;

@Service(value = "statusService")
public class StatusService {

    Logger logger = LoggerFactory.getLogger(StatusService.class);

    private DatabaseService databaseService;
    private Set<String> stageOneOperations;
    private Set<String> stageTwoOperations;
    public enum Operations {DISABLED, READY_FOR_REALISATION, IN_REALISATION, DONE};


    public StatusService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.stageOneOperations = new HashSet<>();

        this.stageOneOperations.add("Cutting");
        this.stageOneOperations.add("Sanding");
        this.stageOneOperations.add("Drilling");
        this.stageOneOperations.add("CNC");

        this.stageTwoOperations = new HashSet<>();
        this.stageTwoOperations.add("Hardening");
        this.stageTwoOperations.add("Enamelling");
        this.stageTwoOperations.add("Lamination");

    }

    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {

        Order order = databaseService.getOrder(changeStatusDto.getOrderId());
        Item item = databaseService.getItem(changeStatusDto.getItemId());
        Operation operation = databaseService.getOperation(changeStatusDto.getOperationId());
        Operations newStatus = Operations.valueOf(changeStatusDto.getNewStatus());

        operation.setStatus(newStatus.toString());

        if(newStatus == Operations.IN_REALISATION) {
            item.setStatus(Operations.IN_REALISATION.toString());
            disableOtherOperationsInStage(item, operation);
        } else if (newStatus == Operations.DONE) {
            logger.debug("DONE");
            if(stageOneOperations.contains(operation.getName())) {
                logger.debug("StageOneOperation");
                int stageOneCounter = countStageOneOperations(item);
                logger.debug("stageOneCounter=" + stageOneCounter);
                if(stageOneCounter > 0){
                    enableOtherOperationsInStage(item, operation);
                } else {
                    enableStageTwoOperations(item);
                }
            } else {
                enableOtherOperationsInStage(item, operation);
            }

        }

        databaseService.updateOrder(order);

        return databaseService.fetchOrder(changeStatusDto.getOrderId());

    }

    private void disableOtherOperationsInStage(Item item, Operation operation) {
        if(stageOneOperations.contains(operation.getName())) {
            for(Operation otherOperation : item.getOperations()) {
                if(stageOneOperations.contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(Operations.DONE.toString())) {
                    otherOperation.setStatus(Operations.DISABLED.toString());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.contains(otherOperation.getName()) && otherOperation != operation && !otherOperation.getStatus().equals(Operations.DONE.toString())) {
                    otherOperation.setStatus(Operations.DISABLED.toString());
                }
            }
        }
    }

    private int countStageOneOperations(Item item) {
        int counter = 0;
        for(Operation operation : item.getOperations()) {
            if(stageOneOperations.contains(operation.getName()) && !operation.getStatus().equals(Operations.DONE.toString())) {
                counter++;
            }
        }
        return counter;
    }

    private void enableOtherOperationsInStage(Item item, Operation operation) {
        logger.debug("enableOtherOperationsInStage");
        if(stageOneOperations.contains(operation.getName())) {
            logger.debug("stageOne");
            for(Operation otherOperation : item.getOperations()) {
                logger.debug("otherOperation.getName() = " + otherOperation.getName() + ", stageOneOperations.contains(otherOperation.getName()): " + stageOneOperations.contains(otherOperation.getName()) + ", otherOperation != operation:" + (otherOperation != operation) + ", otherOperation.getStatus() = " + otherOperation.getStatus());
                if(stageOneOperations.contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(Operations.DISABLED.toString())) {
                    logger.debug("RFR");
                    otherOperation.setStatus(Operations.READY_FOR_REALISATION.toString());
                }
            }
        } else {
            for(Operation otherOperation : item.getOperations()) {
                if(stageTwoOperations.contains(otherOperation.getName()) && otherOperation != operation && otherOperation.getStatus().equals(Operations.DISABLED.toString())) {
                    otherOperation.setStatus(Operations.READY_FOR_REALISATION.toString());
                }
            }
        }
    }

    private void enableStageTwoOperations(Item item) {
        for(Operation otherOperation : item.getOperations()) {
            if(stageTwoOperations.contains(otherOperation.getName()))
                otherOperation.setStatus(Operations.READY_FOR_REALISATION.toString());
        }
    }

    public void prepareStatuses(AddOrderDto addOrderDto) {
        for(AddItemDto item : addOrderDto.getItems()) {
            for(AddOperationDto operation : item.getOperations()) {
                if(stageOneOperations.contains(operation.getName())) {
                    disableStageTwoOperations(item);
                }
            }
        }
    }

    public void disableStageTwoOperations(AddItemDto item) {
        for(AddOperationDto operation : item.getOperations()) {
            if(stageTwoOperations.contains(operation.getName())) {
                disableOperation(operation);
            }
        }
    }

    public void disableOperation(AddOperationDto operation) {
        operation.setStatus(Operations.DISABLED.toString());
    }
}
