package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Material;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.fetchItemDto.FetchItemDto;
import pl.oncode.glass.web.dto.fetchOperation.FetchOperationDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.updateMaterial.UpdateMaterialDto;
import pl.oncode.glass.web.dto.updateOrder.UpdateOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;
import pl.oncode.glass.service.StatusService.OperationStatus;

import java.util.ArrayList;
import java.util.List;

@Service(value = "mainControllerService")
public class MainControllerService {

    Logger logger = LoggerFactory.getLogger(MainControllerService.class);

    private DatabaseService databaseService;
    private StatusService statusService;

    public MainControllerService(DatabaseService databaseService,
                                 StatusService statusService) {
        this.databaseService = databaseService;
        this.statusService = statusService;
    }

    public List<ViewOrderDto> viewOrders() {
        List<Order> orders = databaseService.getAllOrders();
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();

        orders.forEach(order -> viewOrderDtos.add(ViewOrderDto.createViewOrderDto(order)));

        return viewOrderDtos;
    }

    public FetchOrderDto fetchOrder(int id) {
        Order order = databaseService.getOrder(id);
        return FetchOrderDto.createFetchOrderDto(order);
    }

    public void addOrder(AddOrderDto addOrderDto) {
        Order order = AddOrderDto.createOrder(addOrderDto);
        statusService.prepareStatuses(order);
        databaseService.saveOrder(order);
    }

    public List<ViewMaterialDto> viewMaterials() {
        List<Material> materials = databaseService.getAllMaterials();
        List<ViewMaterialDto> viewMaterialDtos = new ArrayList<>();

        materials.forEach((material -> viewMaterialDtos.add(ViewMaterialDto.createViewMaterialDto(material))));

        return viewMaterialDtos;
    }

    public List<FetchItemDto> fetchItems(Integer orderId) {
        List<Item> items = databaseService.getAllOrderItems(orderId);
        List<FetchItemDto> fetchItemDtos = new ArrayList<>();

        items.forEach(item -> fetchItemDtos.add(FetchItemDto.createFetchItemDto(item)));

        return fetchItemDtos;
    }

    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {
        Operation operation = databaseService.getOperation(changeStatusDto.getOperationId());
        OperationStatus newStatus = OperationStatus.valueOf(changeStatusDto.getNewStatus());

        Order order = statusService.changeOrderStatuses(operation, newStatus);
        databaseService.updateOrder(order);

        return FetchOrderDto.createFetchOrderDto(order);
    }

    public List<FetchItemDto> viewItems() {
        List<Item> items = databaseService.getAllItems();
        List<FetchItemDto> fetchItemDtos = new ArrayList<>();

        items.forEach(item -> fetchItemDtos.add(FetchItemDto.createFetchItemDto(item)));

        return fetchItemDtos;
    }

    public List<FetchOperationDto> fetchItemOperations(Integer itemId) {
        Item item = databaseService.getItem(itemId);
        List<FetchOperationDto> fetchOperationDtos = new ArrayList<>();

        item.getOperations().forEach(operation -> fetchOperationDtos.add(FetchOperationDto.createFetchOperationDto(operation)));

        return fetchOperationDtos;
    }

    public List<FetchOperationDto> viewOperations() {
        List<Operation> operations = databaseService.getAllOperations();
        List<FetchOperationDto> fetchOperationDtos = new ArrayList<>();
        operations.forEach(operation -> fetchOperationDtos.add(FetchOperationDto.createFetchOperationDto(operation)));

        return fetchOperationDtos;
    }

    public FetchOrderDto updateOrder(UpdateOrderDto updateOrderDto) {
        Order newOrder = UpdateOrderDto.createOrder(updateOrderDto);
        statusService.prepareStatuses(newOrder);
        Order oldOrder = databaseService.getOrder(newOrder.getId());
        Order order = updateOrder(newOrder, oldOrder);
        databaseService.updateOrder(order);

        return FetchOrderDto.createFetchOrderDto(order);
    }

    public Order updateOrder(Order newOrder, Order oldOrder) {
        updateItems(oldOrder.getItems(), newOrder.getItems());

        oldOrder.setAttachments(newOrder.getAttachments());
        oldOrder.setExternalOrderId(newOrder.getExternalOrderId());
        oldOrder.setCustomer(newOrder.getCustomer());
        oldOrder.setInvoiceNumber(newOrder.getInvoiceNumber());
        oldOrder.setPrice(newOrder.getPrice());
        oldOrder.setDueDate(newOrder.getDueDate());
        oldOrder.setCreateDate(newOrder.getCreateDate());

        oldOrder.registerRelations();

        return oldOrder;
    }

    private void updateItems(List<Item> oldItems, List<Item> newItems) {
        addNewItems(oldItems, newItems);
    }

    private void addNewItems(List<Item> oldItems, List<Item> newItems) {
        newItems.forEach(item -> {
            if(item.isItemNew()) {
                oldItems.add(item);
            }
        });
    }

    public void updateMaterial(UpdateMaterialDto updateMaterialDto) {
        databaseService.updateMaterial(UpdateMaterialDto.createMaterial(updateMaterialDto));
    }
}
