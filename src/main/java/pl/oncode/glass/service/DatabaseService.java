package pl.oncode.glass.service;

import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.OrderDAO;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseService {

    private OrderDAO dao;

    public DatabaseService(OrderDAO dao) {
        this.dao = dao;
    }

    public Order findOrder(Integer id) {
        return dao.findOne(id);
    }

    public List<Order> getAllOrders() {
        return dao.findAll();
    }

    public void updateOrder(Order order) {
        dao.update(order);
    }

    public void createOrder(Order order) {
        dao.create(order);
    }

    public void deleteOrder(Order Order) {
        dao.delete(Order);
    }

    private void testDb(){
        createFakeOrder("Sky Tower");
        createFakeOrder("Galeria Dominikańska");
        createFakeOrder("OVO");
        createFakeOrder("Arkady Wrocławskie");

    }

    private Order createFakeOrder(String customer) {
        Order order = new Order("Order/2019/07/12/FZ/54321",
                customer,
                "54321",
                BigDecimal.valueOf(50000.00),
                Date.valueOf("2019-09-30"),
                Date.valueOf(LocalDate.now()));
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(order.getId(), 1, 100.0, 100.0, 10.0, 10);
        Item item2 = new Item(order.getId(), 2, 200.0, 200.0, 10.0, 10);
        Item item3 = new Item(order.getId(), 3, 100.0, 100.0, 10.0, 10);
        Item item4 = new Item(order.getId(), 4, 100.0, 100.0, 10.0, 10);

        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(item1.getId(), "Drilling"));
        operations.add(new Operation(item1.getId(), "Smoothing"));
        operations.add(new Operation(item1.getId(), "Cutting"));
        operations.add(new Operation(item1.getId(), "CNC"));
        operations.add(new Operation(item1.getId(), "Shocking"));

        item1.setOperations(operations);

        operations.forEach((operation)->operation.setItemId(item2.getId()));
        item2.setOperations(operations);

        operations.forEach((operation)->operation.setItemId(item3.getId()));
        item3.setOperations(operations);

        operations.forEach((operation)->operation.setItemId(item4.getId()));
        item4.setOperations(operations);

        items.addAll(Arrays.asList(item1, item2, item3, item4));
        order.setItems(items);

        return order;
    }

}
