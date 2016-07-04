package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.Order;

import java.util.List;

/**
 * Contains business-logic operations that bound with driver.
 */
public interface DriverService extends GenericService<Driver> {
    /**
     * Gets driver by number.
     * @param driverNumber driver number
     * @return driver
     */
    Driver getByNumber(int driverNumber);

    /**
     * Deletes driver by number.
     * @param driverNumber driver number
     */
    void deleteByNumber(int driverNumber);

    /**
     * Changes name fields of a driver by number.
     * @param driverNumber driver number
     * @param firstName first name
     * @param lastName last name
     */
    void changeByNumber(int driverNumber, String firstName, String lastName);

    /**
     * Creates a new driver with passed values.
     * @param firstName first name
     * @param lastName last name
     * @param city current city
     */
    void createDriver(String firstName, String lastName, String city);

    /**
     * Gets a list of all existing drivers.
     * @return drivers list
     */
    List<Driver> getAllDrivers();

    /**
     * Gets all drivers that suitable for assigning of the order given order.
     * @param order order
     * @return drivers list
     */
    List<Driver> getSuitableDriversByOrder(Order order);
}
