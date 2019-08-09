package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.ItemsDao;
import pl.oncode.glass.dao.MaterialDao;
import pl.oncode.glass.dao.OrderDao;
import pl.oncode.glass.model.*;
import pl.oncode.glass.web.dto.addOrder.AddItemDto;
import pl.oncode.glass.web.dto.addOrder.AddOperationDto;
import pl.oncode.glass.web.dto.addOrder.AddOrderDto;
import pl.oncode.glass.web.dto.fetchItemDto.FetchItemDto;
import pl.oncode.glass.web.dto.fetchOrder.FetchOrderDto;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;
import pl.oncode.glass.web.dto.viewOrders.ViewOrderDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service(value = "databaseService")
public class DatabaseService {

    private Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    private OrderDao orderDao;
    private MaterialDao materialDao;
    private ItemsDao itemsDao;

    public DatabaseService(OrderDao orderDao, MaterialDao materialDao, ItemsDao itemsDao) {
        this.orderDao = orderDao;
        this.materialDao = materialDao;
        this.itemsDao = itemsDao;
//        testDb();
    }

    // orderDao methods

    private Order getOrder(Integer id) {
        return orderDao.get(id);
    }

    private List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    private void updateOrder(Order order) {
        orderDao.update(order);
    }

    private void saveOrder(Order order) {
        orderDao.save(order);
    }

    private void deleteOrder(Order Order) {
        orderDao.delete(Order);
    }

    // materialDao methods

    private Material getMaterial(Integer id) {
        return materialDao.get(id);
    }

    private List<Material> getAllMaterials() {
        return materialDao.getAll();
    }

    private void updateMaterial(Material material) {
        materialDao.update(material);
    }

    private void saveMaterial(Material material) {
        materialDao.save(material);
    }

    private void deleteMaterial(Material material) {
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
                .setDueDate(LocalDate.of(2019, 9, 30))
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
            .setDueDate(addOrderDto.getDueDate())
            .setStatus(addOrderDto.getStatus())
            .createOrder();

        for(AddItemDto addItemDto : addOrderDto.getItems()){

            Item item = new Item.ItemBuilder()
                .setMaterialId(addItemDto.getMaterialId())
                .setWidth(addItemDto.getWidth())
                .setHeight(addItemDto.getHeight())
                .setDepth(addItemDto.getDepth())
                .setQuantity(addItemDto.getQuantity())
                .setStatus(addItemDto.getStatus())
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

    private ViewOrderDto createViewOrderDto(Order order) {

        return new ViewOrderDto.ViewOrderDtoBuilder()
                .setId(order.getId())
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setDueDate(order.getDueDate())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrderDto();
    }

    //fetch order
    public FetchOrderDto fetchOrder(int id) {
        return createFetchDto(getOrder(id));
    }

    private FetchOrderDto createFetchDto(Order order) {
        return new FetchOrderDto.ViewOrderDtoBuilder()
                .setId(order.getId())
                .setItems(order.getItems())
                .setAttachments(order.getAttachments())
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setDueDate(order.getDueDate())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrderDto();
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

        return new ViewMaterialDto(
                material.getId(),
                material.getName(),
                material.getDescription());
    }

    // fetch Items
    public List<FetchItemDto> fetchItems(Integer orderId) {
        logger.debug("orderId=" + orderId);
        Order order = orderDao.get(orderId);
        logger.debug("Order=" + order.getCustomer());
        List<Item> items = itemsDao.getAll(order);
        List<FetchItemDto> fetchItemDtos = new ArrayList<>();

        for(Item item : items) {
            fetchItemDtos.add(createFetchItemDto(item));
        }

        return fetchItemDtos;
    }

    private FetchItemDto createFetchItemDto(Item item) {
        return new FetchItemDto.FetchItemDtoBuilder()
                .setId(item.getId())
                .setOrder(item.getOrder())
                .setMaterialId(item.getMaterialId())
                .setWidth(item.getWidth())
                .setHeight(item.getHeight())
                .setDepth(item.getDepth())
                .setQuantity(item.getQuantity())
                .setStatus(item.getStatus())
                .setNote(item.getNote())
                .createFetchItemDto();
    }
}
