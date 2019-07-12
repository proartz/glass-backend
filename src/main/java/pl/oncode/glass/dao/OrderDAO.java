package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import pl.oncode.glass.model.Order;

@Repository
public class OrderDAO extends AbstractJpaDAO<Order> {

    public OrderDAO() {
        setClazz(Order.class);
    }
}
