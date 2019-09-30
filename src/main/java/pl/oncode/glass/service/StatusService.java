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

    public enum OperationStatus {ZAPLANOWANE, GOTOWE_DO_REALIZACJI, W_REALIZACJI, ZROBIONE}

    ;

    public enum OrderStatus {PRZYJĘTO, W_REALIZACJI, WYDANE, ROZLICZONE}

    ;

    public enum Operations {CIĘCIE, SZLIFOWANIE, WIERCENIE, CNC, HARTOWANIE, EMALIOWANIE, LAMINOWANIE, WYDANIE}

    ;

    public StatusService(StageOperations allOperations) {
        this.allOperations = allOperations;
    }

    public Order changeOrderStatuses(Operation operation, OperationStatus newStatus) {
        Item item = operation.getItem();
        Order order = item.getOrder();
        List<Operation> operations = item.getOperations();

        operation.setStatus(newStatus.name());

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

    public void prepareStatuses(Order order) {
        order.setStatus(OrderStatus.PRZYJĘTO.name());
        for (Item item : order.getItems()) {
            prepareItemStatuses(item);
        }
    }

    public void prepareItemStatuses(Item item) {
        // enable first operation
        item.getOperations().get(0).setStatus(OperationStatus.GOTOWE_DO_REALIZACJI.name());
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
