package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OperationDao implements Dao<Operation>{

    @PersistenceContext
    private EntityManager entityManager;

    // standard constructors

    @Override
    public  Operation get(Integer id) {
        return entityManager.find(Operation.class, id);
    }

    @Override
    public List<Operation> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Operation e");
        return query.getResultList();
    }

    public List<Item> getAllItemOperations(Item item) {
        Query query = entityManager.createQuery("SELECT i FROM Operation i WHERE i.item = :item");
        return query.setParameter("item", item)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(Operation operation) {
        entityManager.persist(operation);
    }

    @Override
    @Transactional
    public void update(Operation operation) {
        entityManager.merge(operation);
    }

    @Override
    @Transactional
    public void delete(Operation operation) {
        entityManager.remove(operation);
    }
    
}
