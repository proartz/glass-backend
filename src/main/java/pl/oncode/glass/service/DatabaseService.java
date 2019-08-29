package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.ItemDao;
import pl.oncode.glass.dao.MaterialDao;
import pl.oncode.glass.dao.OperationDao;
import pl.oncode.glass.dao.OrderDao;
import pl.oncode.glass.model.*;
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

    // itemDao methods

    public Item getItem(Integer id) { return itemDao.get(id);}

    public List<Item> getAllItems() { return itemDao.getAll();}

    public List<Item> getAllOrderItems(Integer orderId) {
        Order order = getOrder(orderId);
        return order.getItems();
    }

    // operationDao methods

    public Operation getOperation(Integer id) { return operationDao.get(id);}

}
