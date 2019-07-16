package pl.oncode.glass.service;

import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.OrderDao;
import pl.oncode.glass.model.Attachment;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service(value = "databaseService")
public class DatabaseService {

    private OrderDao dao;

    public DatabaseService(OrderDao dao) {
        this.dao = dao;
//        testDb();
    }

    // DAO methods

    public Order getOrder(Integer id) {
        return dao.get(id);
    }

    public List<Order> getAllOrders() {
        return dao.getAll();
    }

    public void updateOrder(Order order) {
        dao.update(order);
    }

    public void saveOrder(Order order) {
        dao.save(order);
    }

    public void deleteOrder(Order Order) {
        dao.delete(Order);
    }

    // model creation methods

    private void testDb(){
        saveOrder(createFakeOrder("Sky Tower"));
        saveOrder(createFakeOrder("Galeria Dominikańska"));
        saveOrder(createFakeOrder("OVO"));
        saveOrder(createFakeOrder("Arkady Wrocławskie"));
    }

    private Order createFakeOrder(String customer) {
        Order order = new Order.OrderBuilder()
                .addItem(new Item.ItemBuilder()
                    .setMaterialId(1)
                    .addOperation(new Operation("Drilling"))
                    .addOperation(new Operation("Smoothing"))
                    .addOperation(new Operation("Cutting"))
                    .addOperation(new Operation("CNC"))
                    .addOperation(new Operation("Shocking"))
                    .setWidth(100.0)
                    .setHeight(100.0)
                    .setDepth(10.0)
                    .setQuantity(10)
                    .createItem())
                .addItem(new Item.ItemBuilder()
                    .setMaterialId(2)
                    .addOperation(new Operation("Drilling"))
                    .addOperation(new Operation("Smoothing"))
                    .addOperation(new Operation("Cutting"))
                    .addOperation(new Operation("CNC"))
                    .addOperation(new Operation("Shocking"))
                    .setWidth(200.0)
                    .setHeight(200.0)
                    .setDepth(10.0)
                    .setQuantity(10)
                    .createItem())
                .addItem(new Item.ItemBuilder()
                    .setMaterialId(3)
                    .addOperation(new Operation("Drilling"))
                    .addOperation(new Operation("Smoothing"))
                    .addOperation(new Operation("Cutting"))
                    .addOperation(new Operation("CNC"))
                    .addOperation(new Operation("Shocking"))
                    .setWidth(100.0)
                    .setHeight(100.0)
                    .setDepth(10.0)
                    .setQuantity(10)
                    .createItem())
                .addItem(new Item.ItemBuilder()
                    .setMaterialId(4)
                    .addOperation(new Operation("Drilling"))
                    .addOperation(new Operation("Smoothing"))
                    .addOperation(new Operation("Cutting"))
                    .addOperation(new Operation("CNC"))
                    .addOperation(new Operation("Shocking"))
                    .setWidth(100.0)
                    .setHeight(100.0)
                    .setDepth(10.0)
                    .setQuantity(10)
                    .createItem())
                .setExternalOrderId("Order/2019/07/12/FZ/54321")
                .setCustomer(customer)
                .setPrice(BigDecimal.valueOf(50000.00))
                .setRealisationDate(Date.valueOf("2019-09-30"))
                .setStatus("NEW")
                .createOrder();

        // register relationship of entities for JPA

        registerRelations(order);

        return order;
    }

    private void registerRelations(Order order) {
        for(Item item : order.getItems()) {
            for(Operation operation : item.getOperations()) {
                operation.setItem(item);
            }
            item.setOrder(order);
        }

        for(Attachment attachment : order.getAttachments()) {
            attachment.setOrder(order);
        }
    }

    // add Order

    public void addOrder(AddOrderDto addOrderDto) {
        saveOrder(createOrder(addOrderDto));
    }

    public Order createOrder(AddOrderDto addOrderDto) {

        Order order = new Order.OrderBuilder()
            .setExternalOrderId(addOrderDto.getExternalOrderId())
            .setCustomer(addOrderDto.getCustomer())
            .setInvoiceNumber(addOrderDto.getInvoiceNumber())
            .setPrice(addOrderDto.getPrice())
            .setRealisationDate(addOrderDto.getRealisationDate())
            .setStatus(addOrderDto.getStatus())
            .createOrder();

        for(AddItemDto addItemDto : addOrderDto.getItems()){

            Item item = new Item.ItemBuilder()
                .setMaterialId(addItemDto.getMaterialId())
                .setWidth(addItemDto.getWidth())
                .setHeight(addItemDto.getHeight())
                .setDepth(addItemDto.getDepth())
                .setQuantity(addItemDto.getQuantity())
                .setNote(addItemDto.getNote())
                .createItem();

            for(AddOperationDto addOperationDto : addItemDto.getOperations()) {

                item.getOperations().add(
                        new Operation(addOperationDto.getName(),
                                addOperationDto.getStatus()));
            }

            order.getItems().add(item);
        }

        registerRelations(order);

        return order;
    }

}
