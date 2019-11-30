package pl.oncode.glass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.oncode.glass.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        return (User)query.setParameter("username", username)
                .getSingleResult();
    }

    public User findById(long id) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id");
        return (User)query.setParameter("id", id)
                .getSingleResult();
    }

    public List<User> findAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Transactional
    public void deleteAll() {
        Query query = entityManager.createQuery("DELETE FROM User");
        query.executeUpdate();
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void saveAll(List<User> users) {
        users.forEach(p -> entityManager.persist(p));
    }
}
