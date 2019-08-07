package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemsDao implements Dao<Item>{

    @PersistenceContext
    private EntityManager entityManager;

    // standard constructors

    @Override
    public Item get(Integer id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public List<Item> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Item e");
        return query.getResultList();
    }

    public List<Item> getAll(Order order) {
        Query query = entityManager.createQuery("SELECT i FROM Item i WHERE i.order = :order");
        return query.setParameter("order", order)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(Item Item) {
        entityManager.persist(Item);
    }

    @Override
    @Transactional
    public void update(Item Item) {
        entityManager.merge(Item);
    }

    @Override
    @Transactional
    public void delete(Item Item) {
        entityManager.remove(Item);
    }
    
}
