package cargotransportations.dao;

import java.util.List;

public interface GenericDao<T> {
    void create(T object);
    T read(int id);
    void update(T object);
    void delete(T object);
    List<T> getAll();
}
