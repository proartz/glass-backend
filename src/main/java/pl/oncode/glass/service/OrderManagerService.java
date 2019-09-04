package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Attachment;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.updateOrder.UpdateItemDto;
import pl.oncode.glass.web.dto.updateOrder.UpdateOperationDto;
import pl.oncode.glass.web.dto.updateOrder.UpdateOrderDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;

import java.util.List;

@Service(value = "orderManagerService")
public class OrderManagerService {

    private Logger logger = LoggerFactory.getLogger(OrderManagerService.class);

    public OrderManagerService() {
    }

    public Order createOrder(AddOrderDto addOrderDto) {

        Order order = new Order.OrderBuilder()
                .setExternalOrderId(addOrderDto.getExternalOrderId())
                .setCustomer(addOrderDto.getCustomer())
                .setInvoiceNumber(addOrderDto.getInvoiceNumber())
                .setPrice(addOrderDto.getPrice())
                .setDueDate(addOrderDto.getDueDate())
                .setStatus(addOrderDto.getStatus())
                .createOrder();

        for(AddItemDto addItemDto : addOrderDto.getItems()){

            Item item = new Item.ItemBuilder()
                    .setMaterialId(addItemDto.getMaterialId())
                    .setWidth(addItemDto.getWidth())
                    .setHeight(addItemDto.getHeight())
                    .setDepth(addItemDto.getDepth())
                    .setQuantity(addItemDto.getQuantity())
                    .setStatus(addItemDto.getStatus())
                    .setNote(addItemDto.getNote())
                    .createItem();

            for(AddOperationDto addOperationDto : addItemDto.getOperations()) {

                item.getOperations().add(
                        new Operation(null, addOperationDto.getName(),
                                addOperationDto.getStatus()));
            }

            order.getItems().add(item);
        }

        registerRelations(order);

        return order;
    }


    public Order createOrder(UpdateOrderDto updateOrderDto) {
        Order order = new Order.OrderBuilder()
                .setId(updateOrderDto.getId())
                .setExternalOrderId(updateOrderDto.getExternalOrderId())
                .setCustomer(updateOrderDto.getCustomer())
                .setInvoiceNumber(updateOrderDto.getInvoiceNumber())
                .setPrice(updateOrderDto.getPrice())
                .setDueDate(updateOrderDto.getDueDate())
                .setStatus(updateOrderDto.getStatus())
                .createOrder();

        for(UpdateItemDto updateItemDto : updateOrderDto.getItems()){

            Item item = new Item.ItemBuilder()
                    .setId(updateItemDto.getId())
                    .setMaterialId(updateItemDto.getMaterialId())
                    .setWidth(updateItemDto.getWidth())
                    .setHeight(updateItemDto.getHeight())
                    .setDepth(updateItemDto.getDepth())
                    .setQuantity(updateItemDto.getQuantity())
                    .setStatus(updateItemDto.getStatus())
                    .setNote(updateItemDto.getNote())
                    .createItem();

            for(UpdateOperationDto updateOperationDto : updateItemDto.getOperations()) {

                item.getOperations().add(
                        new Operation(updateOperationDto.getId(), updateOperationDto.getName(),
                                updateOperationDto.getStatus()));
            }

            order.getItems().add(item);
        }

        registerRelations(order);

        return order;
    }

    private void registerRelations(Order order) {
        for(Item item : order.getItems()) {
            for(Operation operation : item.getOperations()) {
                operation.setItem(item);
            }
            item.setOrder(order);
        }

        for(Attachment attachment : order.getAttachments()) {
            attachment.setOrder(order);
        }
    }

    public ViewOrderDto createViewOrderDto(Order order) {

        return new ViewOrderDto.ViewOrderDtoBuilder()
                .setId(order.getId())
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setDueDate(order.getDueDate())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrderDto();
    }

    public FetchOrderDto createFetchOrderDto(Order order) {
        return new FetchOrderDto.ViewOrderDtoBuilder()
                .setId(order.getId())
                .setItems(order.getItems())
                .setAttachments(order.getAttachments())
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setDueDate(order.getDueDate())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrderDto();
    }

    public Order updateOrder(Order newOrder, Order oldOrder) {
        oldOrder.setStatus(newOrder.getStatus());
//        oldOrder.setItems(newOrder.getItems());
        updateItems(oldOrder.getItems(), newOrder.getItems());

        oldOrder.setAttachments(newOrder.getAttachments());
        oldOrder.setExternalOrderId(newOrder.getExternalOrderId());
        oldOrder.setCustomer(newOrder.getCustomer());
        oldOrder.setInvoiceNumber(newOrder.getInvoiceNumber());
        oldOrder.setPrice(newOrder.getPrice());
        oldOrder.setDueDate(newOrder.getDueDate());
        oldOrder.setCreateDate(newOrder.getCreateDate());

        registerRelations(oldOrder);

        return oldOrder;
    }

    private void updateItems(List<Item> oldItems, List<Item> newItems) {
        addNewItems(oldItems, newItems);
//        removeUnnecessaryItems(oldItems, newItems);
    }

    private void removeUnnecessaryItems(List<Item> oldItems, List<Item> newItems) {
        oldItems.forEach(item -> {
            if(!newItems.contains(item)) {
                oldItems.remove(item);
            }
        });
    }

    private void addNewItems(List<Item> oldItems, List<Item> newItems) {
        newItems.forEach(item -> {
            if(item.isItemNew()) {
                oldItems.add(item);
            }
//            if(!oldItems.contains(item)) {
//                item.setId(null);
//                oldItems.add(item);
//            }
        });
    }
}
