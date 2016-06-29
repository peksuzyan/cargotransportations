package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Driver;

import java.util.List;

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

    /**
     * Gets free drivers.
     * @return drivers list
     */
    List<Driver> getFreeDrivers();
}
