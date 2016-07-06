package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.service.interfaces.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements business-logic operations that bound with any entity.
 */
public abstract class GenericServiceImpl<T> implements GenericService<T> {
    /**
     * Gets an instance of dao implementation in this service.
     * @return an instance of dao implementation
     */
    abstract GenericDao<T> getDao();

    @Transactional
    @Override
    public void create(T object) {
        getDao().create(object);
    }

    @Transactional
    @Override
    public void update(T object) {
        getDao().update(object);
    }

    @Transactional
    @Override
    public void delete(T object) {
        getDao().delete(object);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAllByRange(int pageNumber,
                                 int countOnPage,
                                 String sortBy,
                                 String sortTo) {
        int firstItem = countOnPage * (pageNumber - 1);
        return getDao().getAllByRange(firstItem, countOnPage, sortBy, sortTo);
    }
}
