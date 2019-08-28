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
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;
import pl.oncode.glass.service.StatusService.OperationStatus;

import java.util.ArrayList;
import java.util.List;

@Service(value = "mainControllerService")
public class MainControllerService {

    Logger logger = LoggerFactory.getLogger(MainControllerService.class);

    private DatabaseService databaseService;
    private ItemManagerService itemManagerService;
    private MaterialManagerService materialManagerService;
    private OrderManagerService orderManagerService;
    private StatusService statusService;

    public MainControllerService(DatabaseService databaseService,
                                 ItemManagerService itemManagerService,
                                 MaterialManagerService materialManagerService,
                                 OrderManagerService orderManagerService,
                                 StatusService statusService) {
        this.databaseService = databaseService;
        this.itemManagerService = itemManagerService;
        this.materialManagerService = materialManagerService;
        this.orderManagerService = orderManagerService;
        this.statusService = statusService;
    }

    public List<ViewOrderDto> viewOrders() {
        List<Order> orders = databaseService.getAllOrders();
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();

        orders.forEach(order -> viewOrderDtos.add(orderManagerService.createViewOrderDto(order)));

        return viewOrderDtos;
    }

    public FetchOrderDto fetchOrder(int id) {
        Order order = databaseService.getOrder(id);
        return orderManagerService.createFetchOrderDto(order);
    }

    public void addOrder(AddOrderDto addOrderDto) {
        Order order = orderManagerService.createOrder(addOrderDto);
        statusService.prepareStatuses(order);
        databaseService.saveOrder(order);
    }

    public List<ViewMaterialDto> viewMaterials() {
        List<Material> materials = databaseService.getAllMaterials();
        List<ViewMaterialDto> viewMaterialDtos = new ArrayList<>();

        materials.forEach((material -> viewMaterialDtos.add(materialManagerService.createViewMaterialDto(material))));

        return viewMaterialDtos;
    }

    public List<FetchItemDto> fetchItems(Integer orderId) {
        List<Item> items = databaseService.getAllOrderItems(orderId);
        List<FetchItemDto> fetchItemDtos = new ArrayList<>();

        items.forEach(item -> fetchItemDtos.add(itemManagerService.createFetchItemDto(item)));

        return fetchItemDtos;
    }

    public FetchOrderDto changeOrderStatuses(ChangeStatusDto changeStatusDto) {
        Operation operation = databaseService.getOperation(changeStatusDto.getOperationId());
        OperationStatus newStatus = OperationStatus.valueOf(changeStatusDto.getNewStatus());
        Order order = statusService.changeOrderStatuses(operation, newStatus);
        databaseService.updateOrder(order);

        return orderManagerService.createFetchOrderDto(order);
    }
}
