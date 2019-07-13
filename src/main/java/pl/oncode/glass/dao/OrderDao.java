package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.oncode.glass.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class OrderDao implements Dao<Order>{

    @PersistenceContext
    private EntityManager entityManager;

    // standard constructors

    @Override
    public Order get(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Order e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        entityManager.remove(order);
    }
    
}
