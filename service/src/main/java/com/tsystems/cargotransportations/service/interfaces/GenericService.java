package com.tsystems.cargotransportations.service.interfaces;

import java.util.List;

public interface GenericService<T> {

    /**
     * Creates passed object in the dao layer.
     * @param object object
     */
    void create(T object);

    /**
     * Gets object by passed id.
     * @param id id
     * @return object
     */
    T read(int id);

    /**
     * Updates passed object in the dao layer.
     * @param object object
     */
    void update(T object);

    /**
     * Deletes passed object in the dao layer.
     * @param object object
     */
    void delete(T object);

    /**
     * Gets total entities count.
     * @return entities count
     */
    int getTotalCount();

    /**
     * Gets all existed objects.
     * @return a list of all objects
     */
    List<T> getAll();

    /**
     * Gets a list entities given by parameters.
     * @param pageNumber page number
     * @param countOnPage count on page
     * @param sortBy sort by specified param
     * @param sortTo sort direction
     * @return a list of entities
     */
    List<T> getAllByRange(int pageNumber,
                          int countOnPage,
                          String sortBy,
                          String sortTo);
}
