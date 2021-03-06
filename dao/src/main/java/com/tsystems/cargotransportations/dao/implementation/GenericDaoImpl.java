package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Common DAO implementation for entities management.
 * @param <T>
 */
abstract class GenericDaoImpl<T> implements GenericDao<T> {
    /**
     * Getter of the entity manager.
     * @return entityManager
     */
    abstract EntityManager getEntityManager();

    /**
     * The Class object of parametrized entity.
     */
    private Class<T> genericClass;

    /**
     * Single constructor with generic class as parameter.
     * @param genericClass object Class of entity
     */
    GenericDaoImpl(final Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void create(T object) {
        getEntityManager().persist(object);
    }

    @Override
    public T read(int id) {
        return getEntityManager().find(genericClass, id);
    }

    @Override
    public void update(T object) {
        if (!getEntityManager().contains(object)) {
            getEntityManager().merge(object);
        }
        getEntityManager().flush();
    }

    @Override
    public void delete(T object) {
        getEntityManager().remove(
                getEntityManager().contains(object) ?
                        object : getEntityManager().merge(object));
    }

    @Override
    public int getTotalCount() {
        String query = String.format(
                "SELECT COUNT(t) FROM %s t", genericClass.getSimpleName());
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
        return (int) typedQuery.getSingleResult().longValue();
    }

    @Override
    public List<T> getAll() {
        String query = String.format(
                "FROM %s", genericClass.getSimpleName());
        return getEntityManager().createQuery(query, genericClass).getResultList();
    }

    @Override
    public List<T> getAllByRange(int firstItemNumber,
                                 int itemsCount,
                                 String sortBy,
                                 String sortTo) {
        String query = String.format(
                "FROM %s ORDER BY %s %s", genericClass.getSimpleName(), sortBy, sortTo);
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query, genericClass);
        typedQuery.setFirstResult(firstItemNumber);
        typedQuery.setMaxResults(itemsCount);
        return typedQuery.getResultList();
    }
}
