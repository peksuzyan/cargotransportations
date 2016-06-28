package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.dao.interfaces.DriverDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.dao.implementation.DriverDaoImpl;
import com.tsystems.cargotransportations.dao.implementation.OrderDaoImpl;
import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.DriverService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.tsystems.cargotransportations.constants.MagicConstants.WORKING_TIME_OF_MONTH;

/**
 * Implements business-logic operations that bound with driver.
 */
public class DriverServiceImpl implements DriverService {
    /**
     * Instance of implementation of DriverDao class.
     */
    private DriverDao driverDao = new DriverDaoImpl();
    /**
     * Instance of implementation of OrderDao class.
     */
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Driver getByNumber(int driverNumber) {
        return driverDao.getByNumber(driverNumber);
    }

    @Override
    public void deleteByNumber(int driverNumber) {
        DaoUtils.executeInTransaction(() -> {
            driverDao.delete(driverDao.getByNumber(driverNumber));
        });
    }

    @Override
    public void changeByNumber(int driverNumber, String firstName, String lastName) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = driverDao.getByNumber(driverNumber);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driverDao.update(driver);
        });
    }

    @Override
    public void createDriver(String firstName, String lastName, String city) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = new Driver();
            driverDao.create(driver);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setCity(city);
            driver.setHours(0);
            driver.setStatus(DriverStatus.FREE);
            driver.setNumber(driver.getId() + 100);
        });
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.getAll();
    }

    @Override
    public List<Driver> getSuitableDriversByOrder(Order order) {
        if (order.getTruck() == null)
            return Collections.emptyList();
        if (order.getRoute() == null)
            return Collections.emptyList();
        if (order.getRoute().getCities() == null)
            return Collections.emptyList();
        if (order.getDrivers() == null)
            return Collections.emptyList();
        if (order.getTruck().getPeople() <= order.getDrivers().size())
            return Collections.emptyList();
        List<Driver> drivers = driverDao.getFreeDrivers();
        List<Order> orders = orderDao.getAll();
        Iterator<Driver> iterator = drivers.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            for (Order currentOrder : orders) {
                if (currentOrder.getDrivers().contains(driver)) {
                    iterator.remove();
                    break;
                }
            }
        }
        int restHoursOfMonth = getRestHoursOfMonth();
        int routeHoursInCurrentMonth =
                getRouteHoursInCurrentMonth(
                        order.getRoute().getDuration(), restHoursOfMonth);
        Iterator<Driver> driverIterator = drivers.iterator();
        while (driverIterator.hasNext()) {
            Driver currentDriver = driverIterator.next();
            int expectedTotalWorkingTime =
                    routeHoursInCurrentMonth / order.getTruck().getPeople()
                            + currentDriver.getHours();
            if (expectedTotalWorkingTime > WORKING_TIME_OF_MONTH
                    || !isSameLocationCity(order.getTruck(), currentDriver)) {
                driverIterator.remove();
            }
        }
        return drivers;
    }

    /**
     * Gets whole trip time if time to end of the month is more than trip time
     * or time to the end in hours otherwise.
     * @param routeTime route time
     * @param restTime rest time to end of month in hours
     * @return hours
     */
    private int getRouteHoursInCurrentMonth(int routeTime, int restTime) {
        return routeTime > restTime ? restTime : routeTime;
    }

    /**
     * Gets hours rest of month from current date.
     * @return hours
     */
    private int getRestHoursOfMonth() {
        int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentHourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (lastDayOfMonth - currentDayOfMonth) * 24 + currentHourOfDay;
    }

    /**
     * Returns does driver location equals truck location or not.
     * @param truck truck
     * @param driver driver
     * @return locations equals or not
     */
    private boolean isSameLocationCity(Truck truck, Driver driver) {
        return truck.getCity().equalsIgnoreCase(driver.getCity());
    }
}
