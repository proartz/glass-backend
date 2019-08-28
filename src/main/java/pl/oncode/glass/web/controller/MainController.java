package pl.oncode.glass.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.*;
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

    private OrderManagerService orderManagerService;
    private MaterialManagerService materialManagerService;
    private ItemManagerService itemManagerService;
    private StatusService statusService;

    public MainController(StatusService statusService,
                          OrderManagerService orderManagerService,
                          MaterialManagerService materialManagerService,
                          ItemManagerService itemManagerService) {
        this.statusService = statusService;
        this.orderManagerService = orderManagerService;
        this.materialManagerService = materialManagerService;
        this.itemManagerService = itemManagerService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    List<ViewOrderDto> viewOrders() {
        logger.info("/orders: Received request");
        return orderManagerService.viewOrders();
    }

    @CrossOrigin
    @GetMapping("/order/{id}")
    FetchOrderDto fetchOrder(@PathVariable Integer id) {
        logger.info("/order: id=" + id);
        return orderManagerService.fetchOrder(id);
    }

    @CrossOrigin
    @PostMapping("/order")
    void addOrder(@Valid @RequestBody AddOrderDto addOrderDto) {
        logger.info("/order: " + addOrderDto);
        orderManagerService.addOrder(addOrderDto);
    }

    @CrossOrigin
    @GetMapping("/materials")
    List<ViewMaterialDto> materials() {
        logger.info("/materials: Received request");
        return materialManagerService.viewMaterials();
    }

    @CrossOrigin
    @GetMapping("/items/{orderId}")
    List<FetchItemDto> items(@PathVariable Integer orderId) {
        logger.info("/items: orderId=" + orderId);
        return itemManagerService.fetchItems(orderId);
    }

    @CrossOrigin
    @PostMapping("/changeStatus")
    FetchOrderDto changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
        logger.info("/changeStatus: " + changeStatusDto);
        return orderManagerService.changeOrderStatuses(changeStatusDto);
    }
}
