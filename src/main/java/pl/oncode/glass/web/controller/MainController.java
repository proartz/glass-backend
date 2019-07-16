package pl.oncode.glass.web.controller;

import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.DatabaseService;
import pl.oncode.glass.web.dto.AddOrderDto;

import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    private DatabaseService databaseService;

    public MainController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/orders")
    List<Order> orders() {
        return databaseService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    Order order(@PathVariable Integer id) {
        return databaseService.getOrder(id);
    }

    @PostMapping("/order")
    AddOrderDto addOrder(@RequestBody AddOrderDto addOrderDto) {

        databaseService.addOrder(addOrderDto);
        return addOrderDto;
    }

}
