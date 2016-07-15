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
     * Gets total entities count.
     * @return entities count
     */
    int getTotalCount();

    /**
     * Gets all entities.
     * @return a list of entities
     */
    List<T> getAll();

    /**
     * Gets all entities by specified range.
     * @param firstItemNumber first item number of range (first item has 0 index)
     * @param itemsCount items count of range
     * @param sortBy sort by
     * @param sortTo sort direction
     * @return a list of entities
     */
    List<T> getAllByRange(int firstItemNumber,
                          int itemsCount,
                          String sortBy,
                          String sortTo);
}
