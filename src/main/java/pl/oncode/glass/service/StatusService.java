package pl.oncode.glass.service;

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
            changeItemStatus(Operations.IN_REALISATION);
        }

        databaseService.updateOrder(order);

        return databaseService.fetchOrder(changeStatusDto.getOrderId());

    }

    public void changeItemStatus(Operations newStatus) {

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
