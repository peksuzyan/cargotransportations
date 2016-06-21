package com.tsystems.cargotransportations.dao.abstracts;

import com.tsystems.cargotransportations.entity.Driver;

/**
 * Specific DAO interface for driver entity.
 * Contains specific operations over driver entity.
 */
public interface DriverDao extends GenericDao<Driver> {
    /**
     * Gets driver by number.
     * @param number number
     * @return driver
     */
    Driver getByNumber(int number);
}
