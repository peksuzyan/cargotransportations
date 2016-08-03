package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.DriverDao;
import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import com.tsystems.cargotransportations.exception.DriverIsBusyServiceException;
import com.tsystems.cargotransportations.service.interfaces.DriverService;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.*;

import static com.tsystems.cargotransportations.constants.codes.ExceptionCodes.DRIVER_IS_BUSY;
import static com.tsystems.cargotransportations.constants.mapping.ServiceMapper.DRIVER_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with driver.
 */
@Transactional
@Service(DRIVER_SERVICE)
public class DriverServiceImpl extends GenericServiceImpl<Driver> implements DriverService {

    /**
     * Gets an instance of dao implementation in this service.
     *
     * @return an instance of dao implementation
     */
    @Override
    GenericDao<Driver> getDao() {
        return driverDao;
    }

    /**
     * Instance of implementation of DriverDao class.
     */
    @Autowired
    private DriverDao driverDao;

    /**
     * Checks whether driver is ready to modifying or not in accordance to a business-logic.
     *
     * @param driver driver
     */
    @Transactional(propagation = SUPPORTS)
    @Override
    public boolean isReadyToModifying(Driver driver) {
        if (driver.getStatus() == DriverStatus.BUSY) {
            throw new DriverIsBusyServiceException(DRIVER_IS_BUSY);
        }
        return true;
    }

    /**
     * Checks whether driver is ready to deleting or not in accordance to a business-logic.
     *
     * @param driver driver
     */
    @Override
    public void checkAndDelete(Driver driver) {
        if (isReadyToModifying(driver)) {
            getDao().delete(driver);
        }
    }

    /**
     * Checks whether driver is ready to updating or not in accordance to a business-logic.
     *
     * @param driver driver
     */
    @Override
    public void checkAndUpdate(Driver driver) {
        if (isReadyToModifying(driver)) {
            getDao().update(driver);
        }
    }

    /**
     * Changes driver to a given status.
     *
     * @param email  driver email
     * @param status a new status
     */
    @Override
    public boolean changeStatusByEmail(String email, DriverStatus status) {
        Driver driver = driverDao.getByEmail(email);
        if (driver == null) return false;
        registerWorkedHours(driver, status);
        driver.setStatus(status);
        driverDao.update(driver);
        return true;
    }

    /**
     * Registers a start new shift or completes an early existing one.
     *
     * @param driver driver
     * @param status status
     */
    @Transactional(propagation = SUPPORTS)
    private void registerWorkedHours(Driver driver, DriverStatus status) {
        if (isShiftStart(driver, status)) {
            driver.setShiftStart(new Date());
        } else if (isShiftEnd(driver, status)) {
            calculateWorkedHoursInCurrentMonth(driver);
            driver.setShiftStart(null);
        }
    }

    /**
     * Calculates hours count is worked in current month.
     *
     * @param driver driver
     */
    @Transactional(propagation = SUPPORTS)
    private void calculateWorkedHoursInCurrentMonth(Driver driver) {
        DateTime shiftStart = new DateTime(driver.getShiftStart());
        DateTime shiftEnd = DateTime.now();
        if (shiftStart.monthOfYear() == shiftEnd.monthOfYear()) {
            Period shiftPeriod = new Period(shiftStart, shiftEnd);
            driver.setHours(driver.getHours() + shiftPeriod.getHours());
        } else {
            Period shiftPeriod = new Period(
                    shiftEnd.monthOfYear().withMinimumValue(), shiftEnd);
            driver.setHours(shiftPeriod.getHours());
        }
    }

    /**
     * Returns whether shift is begun.
     *
     * @param driver driver
     * @param status status
     * @return is begun or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isShiftStart(Driver driver, DriverStatus status) {
        return driver.getStatus() == DriverStatus.ASSIGNED
                && (status == DriverStatus.BUSY || status == DriverStatus.REST);
    }

    /**
     * Returns whether shift is completed.
     *
     * @param driver driver
     * @param status status
     * @return is begun or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isShiftEnd(Driver driver, DriverStatus status) {
        return status == DriverStatus.RELAX &&
                (driver.getStatus() == DriverStatus.REST
                        || driver.getStatus() == DriverStatus.BUSY);
    }

    /**
     * Gets a driver by email.
     *
     * @param email email
     * @return driver
     */
    @Transactional(readOnly = true)
    @Override
    public Driver getByEmail(String email) {
        Driver driver = null;
        try {
            driver = driverDao.getByEmail(email);
        } catch (PersistenceException ignore) {
            /* NOP */
        }
        return driver;
    }

}
