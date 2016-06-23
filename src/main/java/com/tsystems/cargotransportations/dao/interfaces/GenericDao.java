package com.tsystems.cargotransportations.dao.interfaces;

import java.util.List;

/**
 * Common DAO interface for all DAO implementations.
 * Contains all common operations over @param entity.
 * @param <T>
 */
public interface GenericDao<T> {
    /**
     * Creates passed entity.
     * @param object entity
     */
    void create(T object);

    /**
     * Gets entity by id.
     * @param id id
     * @return entity
     */
    T read(int id);

    /**
     * Updates passed entity.
     * @param object entity
     */
    void update(T object);

    /**
     * Deletes passed entity.
     * @param object entity
     */
    void delete(T object);

    /**
     * Gets all entities.
     * @return list of entities
     */
    List<T> getAll();
}
