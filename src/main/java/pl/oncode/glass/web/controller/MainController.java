package pl.oncode.glass.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.DatabaseService;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrder.ViewOrderDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private DatabaseService databaseService;

    public MainController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    List<ViewOrderDto> orders() {
        logger.info("/orders: Received request");
        return databaseService.viewOrders();
    }

    @GetMapping("/order/{id}")
    ViewOrderDto order(@PathVariable Integer id) {
        logger.info("/order: Received request");
        logger.info("/order: id=" + id);
        return databaseService.viewOrder(id);
    }

    @CrossOrigin
    @PostMapping("/order")
    void addOrder(@Valid @RequestBody AddOrderDto addOrderDto) {
        logger.info("/addOrder: Received request");
        logger.info("/addOrder: Data received: " + addOrderDto);
        databaseService.addOrder(addOrderDto);
    }

    @CrossOrigin
    @GetMapping("/materials")
    List<ViewMaterialDto> materials() {
        logger.info("/materials: Received request");
        return databaseService.viewMaterials();
    }


}
