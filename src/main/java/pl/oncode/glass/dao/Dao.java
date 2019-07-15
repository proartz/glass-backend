package pl.oncode.glass.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {
    T get(Integer id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}

