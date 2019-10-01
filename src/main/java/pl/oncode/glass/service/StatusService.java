package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.config.StageOperations;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;

import java.util.List;

@Service(value = "statusService")
public class StatusService {

    Logger logger = LoggerFactory.getLogger(StatusService.class);

    StageOperations allOperations;

    public enum OperationStatus {ZAPLANOWANE, GOTOWE_DO_REALIZACJI, W_REALIZACJI, ZROBIONE};
    public enum OrderStatus {PRZYJĘTO, W_REALIZACJI, WYDANE, ROZLICZONE};
    public enum Operations {CIĘCIE, SZLIFOWANIE, WIERCENIE, CNC, HARTOWANIE, EMALIOWANIE, LAMINOWANIE, WYDANIE};

    public StatusService(StageOperations allOperations) {
        this.allOperations = allOperations;
    }

    public Order changeOrderStatuses(Operation operation, OperationStatus newStatus) {
        Item item = operation.getItem();
        Order order = item.getOrder();
        List<Operation> operations = item.getOperations();

        operation.setStatus(newStatus.name());

        // check is this not a RESET operations request
        if(operation.getName().equalsIgnoreCase(Operations.CIĘCIE.name()) &&
            newStatus == OperationStatus.GOTOWE_DO_REALIZACJI) {
            resetOperations(item);
            updateOrderStatusAfterReset(order);
        }

        if (newStatus == OperationStatus.ZROBIONE) {

            if (!operation.getName().equalsIgnoreCase(Operations.WYDANIE.name())) {
                // enable next operation in the item
                int indexOfNextOperation = operations.indexOf(operation) + 1;
                operations.get(indexOfNextOperation).setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());

                // change order status to W_REALIZACJI
                order.setStatus(OrderStatus.W_REALIZACJI.name());
            } else {
                // it's WYDANIE
                if (isItLastOperationToDeliver(order)) {
                    order.setStatus(OrderStatus.WYDANE.name());
                }
            }
        }
        return order;
    }

    private void resetOperations(Item item) {
        for(Operation operation : item.getOperations()) {
            if(!operation.getName().equalsIgnoreCase(Operations.CIĘCIE.name())) {
                operation.setStatus(OperationStatus.ZAPLANOWANE.name());
            }
        }
    }

    private void updateOrderStatusAfterReset(Order order) {
        if(!order.getStatus().equalsIgnoreCase(OrderStatus.PRZYJĘTO.name())) {
            // check if there are any items with the first operation with status different from GOTOWE_DO_REALIZACJI
            // if yes leave the status W_RELIZACJI
            // if no change status to PRZYJĘTO
            order.setStatus(OrderStatus.PRZYJĘTO.name());

            for(Item item : order.getItems()) {
                if(!item.getOperations().get(0).getStatus().equalsIgnoreCase(OperationStatus.GOTOWE_DO_REALIZACJI.name())) {
                    order.setStatus(OrderStatus.W_REALIZACJI.name());
                }
            }
        }
    }

    public void prepareStatuses(Order order) {
        order.setStatus(OrderStatus.PRZYJĘTO.name());
        prepareItemsStatuses(order);
    }

    public void prepareItemsStatuses(Order order) {
        for(Item item : order.getItems()) {
            if(item.isItemNew()) {
                prepareItemStatuses(item);
            }
        }
    }

    public void prepareItemStatuses(Item item) {
        // enable first operation
        item.getOperations().get(0).setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
    }

    public Order updateOrderStatus(Order newOrder) {
        if(newOrder.isContainingNewItems()) {
            prepareItemsStatuses(newOrder);

            if(newOrder.getStatus().equalsIgnoreCase(OrderStatus.WYDANE.name())) {
                newOrder.setStatus(OrderStatus.W_REALIZACJI.name());
            }
        }
        return newOrder;
    }

    private boolean isItLastOperationToDeliver(Order order) {
        // there should be all items with operation WYDANE and status ZROBIONE
        for(Item item : order.getItems()) {
            for(Operation operation : item.getOperations()) {
                if(operation.getName().equalsIgnoreCase(Operations.WYDANIE.name()) &&
                    !operation.getStatus().equalsIgnoreCase(OperationStatus.ZROBIONE.name())) {
                        return false;
                }
            }
        }
        return true;
    }

}
