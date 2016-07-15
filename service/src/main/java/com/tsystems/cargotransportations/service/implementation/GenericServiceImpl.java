package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.service.interfaces.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements business-logic operations that bound with any entity.
 */
@Transactional
abstract class GenericServiceImpl<T> implements GenericService<T> {
    /**
     * Gets an instance of dao implementation in this service.
     * @return an instance of dao implementation
     */
    abstract GenericDao<T> getDao();

    @Override
    public void create(T object) {
        getDao().create(object);
    }

    @Transactional(readOnly = true)
    @Override
    public T read(int id) {
        return getDao().read(id);
    }

    @Override
    public void update(T object) {
        getDao().update(object);
    }

    @Override
    public void delete(T object) {
        getDao().delete(object);
    }

    @Transactional(readOnly = true)
    @Override
    public int getTotalCount() {
        return getDao().getTotalCount();
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> getAllByRange(int pageNumber,
                                 int countOnPage,
                                 String sortBy,
                                 String sortTo) {
        int firstItem = countOnPage * (pageNumber - 1);
        return getDao().getAllByRange(firstItem, countOnPage, sortBy, sortTo);
    }
}
