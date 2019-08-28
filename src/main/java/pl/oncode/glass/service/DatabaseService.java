package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.ItemDao;
import pl.oncode.glass.dao.MaterialDao;
import pl.oncode.glass.dao.OperationDao;
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
    private ItemDao itemDao;
    private OperationDao operationDao;

    public DatabaseService(OrderDao orderDao, MaterialDao materialDao, ItemDao itemDao, OperationDao operationDao) {
        this.orderDao = orderDao;
        this.materialDao = materialDao;
        this.itemDao = itemDao;
        this.operationDao = operationDao;
    }

    // orderDao methods

    public Order getOrder(Integer id) {
        return orderDao.get(id);
    }

    private List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public void saveOrder(Order order) {
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

    public Item getItem(Integer id) { return itemDao.get(id);}

    private List<Item> getAllItems(Integer orderId) {
        Order order = getOrder(orderId);
        return order.getItems();
    }

    public Operation getOperation(Integer id) { return operationDao.get(id);}


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
        List<Item> items = getAllItems(orderId);
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
