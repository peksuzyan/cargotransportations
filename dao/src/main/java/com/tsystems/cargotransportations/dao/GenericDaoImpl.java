package com.tsystems.cargotransportations.dao;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Common DAO implementation for entities management.
 * @param <T>
 */
class GenericDaoImpl<T> implements GenericDao<T> {
    /**
     * Instance of the class Class that is parametrized.
     */
    private Class<T> genericClass;

    GenericDaoImpl(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    /**
     * Gets instance of EntityManager produced by EntityManagerFactory.
     * @return entityManager
     */
    EntityManager getLazyEntityManager() {
        return DaoUtils.getEntityManager();
    }

    @Override
    public void create(T object) {
        getLazyEntityManager().persist(object);
    }

    @Override
    public T read(int id) {
        return getLazyEntityManager().find(genericClass, id);
    }

    @Override
    public void update(T object) {
        getLazyEntityManager().persist(object);
    }

    @Override
    public void delete(T object) {
        getLazyEntityManager().remove(object);
    }

    @Override
    public List<T> getAll() {
        String query = String.format("FROM %s", genericClass.getSimpleName());
        return getLazyEntityManager().createQuery(query, genericClass).getResultList();
    }


}
