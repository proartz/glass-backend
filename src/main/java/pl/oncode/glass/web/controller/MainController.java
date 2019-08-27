package pl.oncode.glass.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.DatabaseService;
import pl.oncode.glass.service.StatusService;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.changeStatus.ChangeStatusDto;
import pl.oncode.glass.web.dto.fetchItemDto.FetchItemDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private DatabaseService databaseService;
    private StatusService statusService;

    public MainController(DatabaseService databaseService, StatusService statusService) {
        this.databaseService = databaseService;
        this.statusService = statusService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    List<ViewOrderDto> viewOrders() {
        logger.info("/orders: Received request");
        return databaseService.viewOrders();
    }

    @CrossOrigin
    @GetMapping("/order/{id}")
    FetchOrderDto fetchOrder(@PathVariable Integer id) {
        logger.info("/order: Received request");
        logger.info("/order: id=" + id);
        return databaseService.fetchOrder(id);
    }

    @CrossOrigin
    @PostMapping("/order")
    void addOrder(@Valid @RequestBody AddOrderDto addOrderDto) {
        logger.info("/order: Received request");
        logger.info("/order: Data received: " + addOrderDto);
        statusService.prepareStatuses(addOrderDto);
        databaseService.addOrder(addOrderDto);
    }

    @CrossOrigin
    @GetMapping("/materials")
    List<ViewMaterialDto> materials() {
        logger.info("/materials: Received request");
        return databaseService.viewMaterials();
    }

    @CrossOrigin
    @GetMapping("/items/{orderId}")
    List<FetchItemDto> items(@PathVariable Integer orderId) {
        logger.info("/items: Received GET request");
        logger.info("/items: orderId=" + orderId);
        return databaseService.fetchItems(orderId);
    }

    @CrossOrigin
    @PostMapping("/changeStatus")
    FetchOrderDto changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
        logger.info("/changeStatus: " + changeStatusDto);
        return statusService.changeOrderStatuses(changeStatusDto);
    }
}
