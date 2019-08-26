package pl.oncode.glass.service;

import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;

@Service(value = "statusService")
public class StatusService {

    DatabaseService databaseService;

    public StatusService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public FetchOrderDto changeStatus(ChangeStatusDto changeStatusDto) {

        Order order = databaseService.getOrder(changeStatusDto.getOrderId());

        for(Item item : order.getItems()) {
            if(item.getId() == changeStatusDto.getItemId()) {
                for (Operation operation : item.getOperations()) {
                    if(operation.getId() == changeStatusDto.getOperationId()) {
                        operation.setStatus(changeStatusDto.getNewStatus());
                    }
                }
            }
        }

        databaseService.updateOrder(order);

        return databaseService.fetchOrder(changeStatusDto.getOrderId());

    }

}
