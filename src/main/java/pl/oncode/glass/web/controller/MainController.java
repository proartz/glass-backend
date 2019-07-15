package pl.oncode.glass.web.controller;

import org.springframework.web.bind.annotation.*;
import pl.oncode.glass.model.Attachment;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.service.DatabaseService;

import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    private DatabaseService dbs;

    public MainController(DatabaseService dbs) {
        this.dbs = dbs;
    }

    @GetMapping("/orders")
    List<Order> orders() {
        return dbs.getAllOrders();
    }

    @GetMapping("/order/{id}")
    Order order(@PathVariable Integer id) {
        return dbs.getOrder(id);
    }

    @PostMapping("/order")
    Order addOrder(@RequestBody Order order) {
        for(Item item : order.getItems()) {
            for(Operation operation : item.getOperations()) {
                operation.setItem(item);
            }
            item.setOrder(order);
        }

        for(Attachment attachment : order.getAttachments()) {
            attachment.setOrder(order);
        }
        dbs.saveOrder(order);
        return order;
    }

}
