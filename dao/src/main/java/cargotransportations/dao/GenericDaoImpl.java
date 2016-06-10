package cargotransportations.dao;

import javax.persistence.EntityManager;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {
    private Class<T> genericClass;

    protected GenericDaoImpl(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    private EntityManager getLazyEntityManager() {
        return DaoUtils.getEntityManager();
    }

    public void create(T object) {
        getLazyEntityManager().persist(object);
    }

    public T read(int id) {
        return getLazyEntityManager().find(genericClass, id);
    }

    public void update(T object) {
        getLazyEntityManager().persist(object);
    }

    public void delete(T object) {
        getLazyEntityManager().remove(object);
    }

    public List<T> getAll() {
        String query = String.format("FROM %s", genericClass.getSimpleName());
        return getLazyEntityManager().createQuery(query, genericClass).getResultList();
    }
}
