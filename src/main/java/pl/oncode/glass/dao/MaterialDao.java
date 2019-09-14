package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.oncode.glass.model.Material;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MaterialDao implements Dao<Material>{

    @PersistenceContext
    private EntityManager entityManager;

    // standard constructors

    @Override
    public Material get(Integer id) {
        return entityManager.find(Material.class, id);
    }

    @Override
    public List<Material> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Material e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Material material) {
        entityManager.persist(material);
    }

    @Override
    @Transactional
    public void update(Material material) {
        entityManager.merge(material);
    }

    @Override
    @Transactional
    public void delete(Material material) {
        Material managedMaterial = entityManager.merge(material);
        entityManager.remove(managedMaterial);
    }
    
}
