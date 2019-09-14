package pl.oncode.glass.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.*;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.deleteMaterial.DeleteMaterialDto;
import pl.oncode.glass.web.dto.fetchItemDto.FetchItemDto;
import pl.oncode.glass.web.dto.fetchOperation.FetchOperationDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.updateMaterial.UpdateMaterialDto;
import pl.oncode.glass.web.dto.updateOrder.UpdateOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private MainControllerService mainControllerService;

    public MainController(MainControllerService mainControllerService) {
        this.mainControllerService = mainControllerService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    List<ViewOrderDto> viewOrders() {
        logger.info("/orders: Received request");
        return mainControllerService.viewOrders();
    }

    @CrossOrigin
    @GetMapping("/order/{id}")
    FetchOrderDto fetchOrder(@PathVariable Integer id) {
        logger.info("/order: id=" + id);
        return mainControllerService.fetchOrder(id);
    }

    @CrossOrigin
    @PostMapping("/order")
    void addOrder(@Valid @RequestBody AddOrderDto addOrderDto) {
        logger.info("/order: " + addOrderDto);
        mainControllerService.addOrder(addOrderDto);
    }

    @CrossOrigin
    @PostMapping("/updateOrder")
    FetchOrderDto updateOrder(@Valid @RequestBody UpdateOrderDto updateOrderDto) {
        logger.info("/updateOrder: " + updateOrderDto);
        return mainControllerService.updateOrder(updateOrderDto);
    }

    @CrossOrigin
    @GetMapping("/materials")
    List<ViewMaterialDto> materials() {
        logger.info("/materials: Received request");
        return mainControllerService.viewMaterials();
    }

    @CrossOrigin
    @PostMapping("/updateMaterial")
    void updateMaterial(@Valid @RequestBody UpdateMaterialDto updateMaterialDto) {
        logger.info("/updateMaterial: " + updateMaterialDto);
        mainControllerService.updateMaterial(updateMaterialDto);
    }

    @CrossOrigin
    @PostMapping("/deleteMaterial")
    void deleteMaterial(@Valid @RequestBody DeleteMaterialDto deleteMaterialDto) {
        logger.info("/deleteMaterial: " + deleteMaterialDto);
        mainControllerService.deleteMaterial(deleteMaterialDto);
    }

    @CrossOrigin
    @GetMapping("/items")
    List<FetchItemDto> items() {
        logger.info("/items: Received request");
        return mainControllerService.viewItems();
    }

    @CrossOrigin
    @GetMapping("/items/{orderId}")
    List<FetchItemDto> items(@PathVariable Integer orderId) {
        logger.info("/items: orderId=" + orderId);
        return mainControllerService.fetchItems(orderId);
    }

    @CrossOrigin
    @GetMapping("/operations")
    List<FetchOperationDto> viewOperations() {
        logger.info("/operations: Received request");
        return mainControllerService.viewOperations();
    }

    @CrossOrigin
    @GetMapping("/operations/{itemId}")
    List<FetchOperationDto> fetchOperations(@PathVariable Integer itemId) {
        logger.info("/operations: itemId=" + itemId);
        return mainControllerService.fetchItemOperations(itemId);
    }

    @CrossOrigin
    @PostMapping("/changeStatus")
    FetchOrderDto changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
        logger.info("/changeStatus: " + changeStatusDto);
        return mainControllerService.changeOrderStatuses(changeStatusDto);
    }
}
