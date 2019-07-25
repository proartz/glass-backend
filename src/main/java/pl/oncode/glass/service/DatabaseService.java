package pl.oncode.glass.service;

import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.MaterialDao;
import pl.oncode.glass.dao.OrderDao;
import pl.oncode.glass.model.*;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrder.ViewOrderDto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service(value = "databaseService")
public class DatabaseService {

    private OrderDao orderDao;
    private MaterialDao materialDao;

    public DatabaseService(OrderDao orderDao, MaterialDao materialDao) {
        this.orderDao = orderDao;
        this.materialDao = materialDao;
//        testDb();
    }

    // orderDao methods

    public Order getOrder(Integer id) {
        return orderDao.get(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    public void deleteOrder(Order Order) {
        orderDao.delete(Order);
    }

    // materialDao methods

    public Material getMaterial(Integer id) {
        return materialDao.get(id);
    }

    public List<Material> getAllMaterials() {
        return materialDao.getAll();
    }

    public void updateMaterial(Material material) {
        materialDao.update(material);
    }

    public void saveMaterial(Material material) {
        materialDao.save(material);
    }

    public void deleteMaterial(Material material) {
        materialDao.delete(material);
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

    private Order createOrder(AddOrderDto addOrderDto) {

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

    // view Order

    public List<ViewOrderDto> viewOrders() {
        List<Order> orders = getAllOrders();
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();

        for(Order order : orders) {
            viewOrderDtos.add(createViewOrderDto(order));
        }

        return viewOrderDtos;
    }

    public ViewOrderDto viewOrder(int id) {
        return createViewOrderDto(getOrder(id));
    }

    private ViewOrderDto createViewOrderDto(Order order) {

        ViewOrderDto viewOrderDto = new ViewOrderDto.ViewOrderBuilder()
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setRealisationDate(order.getRealisationDate())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrder();

        return viewOrderDto;
    }

    // view Materials
    public List<ViewMaterialDto> viewMaterials() {
        List<Material> Materials = getAllMaterials();
        List<ViewMaterialDto> viewMaterialDtos = new ArrayList<>();

        for(Material material : Materials) {
            viewMaterialDtos.add(createViewMaterialDto(material));
        }

        return viewMaterialDtos;
    }

    private ViewMaterialDto createViewMaterialDto(Material material) {

        ViewMaterialDto viewMaterialDto = new ViewMaterialDto(
                material.getId(),
                material.getName(),
                material.getDescription());

        return viewMaterialDto;
    }
}
