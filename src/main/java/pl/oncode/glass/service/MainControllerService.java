package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Material;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addMaterial.AddMaterialDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.deleteItem.DeleteItemDto;
import pl.oncode.glass.web.dto.deleteMaterial.DeleteMaterialDto;
import pl.oncode.glass.web.dto.fetchItem.FetchItemDto;
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

//    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {
//        Operation operation = databaseService.getOperation(changeStatusDto.getOperationId());
//        OperationStatus newStatus = OperationStatus.valueOf(changeStatusDto.getNewStatus());
//
//        Order order = statusService.changeOrderStatuses(operation, newStatus);
//        databaseService.updateOrder(order);
//
//        return FetchOrderDto.createFetchOrderDto(order);
//    }

    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {
        Operation operation = databaseService.getOperation(changeStatusDto.getOperationId());
        Order order = statusService.changeOrderStatuses(operation, OperationStatus.valueOf(changeStatusDto.getNewStatus()));
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
        Order order = statusService.updateOrderStatus(newOrder);
        databaseService.updateOrder(order);
        order = databaseService.getOrder(order.getId());

        return FetchOrderDto.createFetchOrderDto(order);
    }

    public void updateMaterial(UpdateMaterialDto updateMaterialDto) {
        databaseService.updateMaterial(updateMaterialDto.createMaterial());
    }

    public void deleteMaterial(DeleteMaterialDto deleteMaterialDto) {
        databaseService.deleteMaterial(deleteMaterialDto.createMaterial());
    }

    public void addMaterial(AddMaterialDto addMaterialDto) {
        databaseService.saveMaterial(addMaterialDto.createMaterial());
    }

    public void deleteItem(DeleteItemDto deleteItemDto) {
        Item item = databaseService.getItem(deleteItemDto.getId());
        databaseService.deleteItem(item);
    }
}
