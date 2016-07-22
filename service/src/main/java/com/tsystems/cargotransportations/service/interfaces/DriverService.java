package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import com.tsystems.cargotransportations.entity.Order;

import java.util.List;

/**
 * Contains business-logic operations that bound with driver.
 */
public interface DriverService extends GenericService<Driver> {

    /**
     * Checks whether driver is ready to modifying or not in accordance to a business-logic.
     * @param driver driver
     */
    boolean isReadyToModifying(Driver driver);

    /**
     * Checks whether driver is ready to deleting or not in accordance to a business-logic.
     * @param driver driver
     */
    void checkAndDelete(Driver driver);

    /**
     * Checks whether driver is ready to updating or not in accordance to a business-logic.
     * @param driver driver
     */
    void checkAndUpdate(Driver driver);

    /**
     * Gets a driver by email.
     * @param email email
     * @return driver
     */
    Driver getByEmail(String email);

    /**
     * Gets all drivers that suitable for assigning of the order given order.
     * @param order order
     * @return drivers list
     */
    List<Driver> getSuitableDriversByOrder(Order order);

    /**
     * Changes driver on a given status.
     * @param email driver email
     * @param status a new status
     * @return is changed or not
     */
    boolean changeStatusByEmail(String email, DriverStatus status);

}
