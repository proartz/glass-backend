package pl.oncode.glass.web.controller;

import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.service.DatabaseService;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrder.ViewOrderDto;

import java.util.List;

@RestController
public class MainController {

    private DatabaseService databaseService;

    public MainController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    List<ViewOrderDto> orders() {
        return databaseService.viewOrders();
    }

    @GetMapping("/order/{id}")
    ViewOrderDto order(@PathVariable Integer id) {
        return databaseService.viewOrder(id);
    }

    @CrossOrigin
    @PostMapping("/order")
    void addOrder(@RequestBody AddOrderDto addOrderDto) {
        databaseService.addOrder(addOrderDto);
    }

    @GetMapping("/materials")
    List<ViewMaterialDto> materials() {
        return databaseService.viewMaterials();
    }


}
