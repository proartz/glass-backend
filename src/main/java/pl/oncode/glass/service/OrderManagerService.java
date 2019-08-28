package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.OrderDao;
import pl.oncode.glass.model.Attachment;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;

import java.util.ArrayList;
import java.util.List;

@Service(value = "orderManagerService")
public class OrderManagerService {

    private Logger logger = LoggerFactory.getLogger(OrderManagerService.class);

    private OrderDao orderDao;
    private DatabaseService databaseService;
    private StatusService statusService;

    public OrderManagerService(OrderDao orderDao, DatabaseService databaseService, StatusService statusService) {
        this.orderDao = orderDao;
        this.databaseService = databaseService;
        this.statusService = statusService;
    }

    public void addOrder(AddOrderDto addOrderDto) {

        Order order = createOrder(addOrderDto);
        statusService.prepareStatuses(order);
        databaseService.saveOrder(order);

    }

    public List<ViewOrderDto> viewOrders() {
        List<Order> orders = databaseService.getAllOrders();
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();

        for(Order order : orders) {
            viewOrderDtos.add(createViewOrderDto(order));
        }

        return viewOrderDtos;
    }

    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {
        Order order = statusService.changeOrderStatuses(changeStatusDto);
        databaseService.updateOrder(order);
        return createFetchDto(order);
    }

    //fetch order
    public FetchOrderDto fetchOrder(int id) {
        Order order = databaseService.getOrder(id);
        return createFetchDto(order);
    }

    private Order createOrder(AddOrderDto addOrderDto) {

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
                        new Operation(addOperationDto.getName(),
                                addOperationDto.getStatus()));
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

    private ViewOrderDto createViewOrderDto(Order order) {

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

    private FetchOrderDto createFetchDto(Order order) {
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

}
