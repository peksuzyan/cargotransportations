package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;

import java.util.List;

/**
 * Specific DAO interface for driver entity.
 * Contains specific operations over driver entity.
 */
public interface DriverDao extends GenericDao<Driver> {

    /**
     * Gets all drivers by a given status.
     * @param status driver status
     * @return drivers list
     */
    List<Driver> getDriversByStatus(DriverStatus status);

    /**
     * Gets driver by given email.
     * @param email email
     * @return driver
     */
    Driver getByEmail(String email);

}
