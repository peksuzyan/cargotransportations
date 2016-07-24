package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.constants.MagicConstants;
import com.tsystems.cargotransportations.dao.interfaces.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.*;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

import java.util.Calendar;
import java.util.List;

import static com.tsystems.cargotransportations.constants.ServiceConstants.*;
import static com.tsystems.cargotransportations.constants.ServiceMapping.ORDER_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with order.
 */
@Transactional
@Service(ORDER_SERVICE)
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {

    /**
     * Gets an instance of dao implementation in this service.
     *
     * @return an instance of dao implementation
     */
    @Override
    GenericDao<Order> getDao() {
        return orderDao;
    }

    /**
     * Instance of implementation of OrderDao class.
     */
    @Autowired
    private OrderDao orderDao;

    /**
     * Instance of implementation of CargoDao class.
     */
    @Autowired
    private CargoDao cargoDao;

    /**
     * Instance of implementation of DriverDao class.
     */
    @Autowired
    private DriverDao driverDao;

    /**
     * Instance of implementation of TruckDao class.
     */
    @Autowired
    private TruckDao truckDao;

    /**
     * Instance of implementation of RouteDao class.
     */
    @Autowired
    private RouteDao routeDao;

    /**
     * Return a list of cargoes by suitable conditions.
     *
     * @return cargoes list
     */
    @Override
    public List<Cargo> getSuitableCargoes() {
        return cargoDao.getCargoesByStatus(CargoStatus.PREPARED);
    }

    /**
     * Returns a list of drivers by suitable conditions.
     *
     * @return drivers list
     */
    @Override
    public List<Driver> getSuitableDrivers() {
        return driverDao.getDriversByStatus(DriverStatus.FREE);
    }

    /**
     * Returns a list of trucks by suitable conditions.
     *
     * @return trucks list
     */
    @Override
    public List<Truck> getSuitableTrucks() {
        return truckDao.getActiveAndFreeTrucks();
    }

    /**
     * Creates order after filling of all needed fields.
     *
     * @param orderId orderId
     */
    @Override
    public void createOrder(int orderId) {
        Order order = orderDao.read(orderId);
        for (Driver driver : order.getDrivers()) {
            driver.setStatus(DriverStatus.ASSIGNED);
            driverDao.update(driver);
        }
        for (Cargo cargo : order.getCargoes()) {
            cargo.setStatus(CargoStatus.SHIPPED);
            cargoDao.update(cargo);
        }
        order.setStatus(OrderStatus.PERFORMING);
        orderDao.update(order);
    }

    /**
     * Adds cargo by id to a given order.
     *
     * @param orderId order id
     * @param cargoId cargo id
     * @return is added or not
     */
    @Override
    public boolean addCargoById(int orderId, int cargoId) {
        try {
            Order order = orderDao.read(orderId);
            order.getCargoes().add(cargoDao.read(cargoId));
            orderDao.update(order);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Clears cargoes list of a given order.
     *
     * @param orderId orderId
     * @return is cleared or not
     */
    @Override
    public boolean clearCargoes(int orderId) {
        try {
            orderDao.read(orderId).getCargoes().clear();
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Assigns truck by id to a given order.
     *
     * @param orderId orderId
     * @param truckId truckId
     * @return is assigned or not
     */
    @Override
    public boolean assignCargoById(int orderId, int truckId) {
        try {
            Order order = orderDao.read(orderId);
            order.setTruck(truckDao.read(truckId));
            orderDao.update(order);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Rejects truck from a given order.
     *
     * @param orderId orderId
     * @return is rejected or not
     */
    @Override
    public boolean rejectTruck(int orderId) {
        try {
            orderDao.read(orderId).setTruck(null);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Adds driver by id to a given order.
     *
     * @param orderId  orderId
     * @param driverId driverId
     * @return is added or not
     */
    @Override
    public boolean addDriverById(int orderId, int driverId) {
        try {
            Order order = orderDao.read(orderId);
            order.getDrivers().add(driverDao.read(driverId));
            orderDao.update(order);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Clears drivers list of a given order.
     *
     * @param orderId orderId
     * @return is cleared or not
     */
    @Override
    public boolean clearDrivers(int orderId) {
        try {
            orderDao.read(orderId).getDrivers().clear();
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Assigns route by id to a given order.
     *
     * @param orderId orderId
     * @param routeId routeId
     * @return is assigned or not
     */
    @Override
    public boolean assignRoute(int orderId, int routeId) {
        try {
            Order order = orderDao.read(orderId);
            order.setRoute(routeDao.read(routeId));
            orderDao.update(order);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Rejects route from a given order.
     *
     * @param orderId orderId
     * @return is rejected or not
     */
    @Override
    public boolean rejectRoute(int orderId) {
        try {
            orderDao.read(orderId).setRoute(null);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether order is ready to modifying or not in accordance to a business-logic.
     *
     * @param order order
     */
    @Transactional(propagation = SUPPORTS)
    @Override
    public boolean isReadyToModifying(Order order) {
        if (order.getStatus() == OrderStatus.PERFORMING) {
            throw new OrderIsPerformingServiceException(ORDER_IS_PERFORMING);
        }
        if (order.getStatus() == OrderStatus.DONE) {
            throw new OrderIsDoneServiceException(ORDER_IS_DONE);
        }
        return true;
    }

    /**
     * Checks that order is ready to performing in according with business-logic.
     *
     * @param order order
     */
    @Transactional(readOnly = true)
    @Override
    public boolean isReadyToPerforming(Order order) {
        return hasCargoes(order)
                && hasTruck(order)
                && hasDrivers(order)
                && hasRoute(order)
                && isActiveTruck(order)
                && isPreparedCargoes(order)
                && isFreeDrivers(order)
                && hasNotTooManyDrivers(order)
                && hasSameLocationsTruckAndRoute(order)
                && hasSameLocationsTruckAndDrivers(order)
                && hasShippingAndDeliveringRightOrder(order)
                && hasTruckWithEnoughCapacity(order)
                && hasDriversWithEnoughWorkingTime(order);
    }

    /**
     * Checks that drivers have enough working time in month in order to perform an order.
     *
     * @param order order
     * @return have or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasDriversWithEnoughWorkingTime(Order order) {
        int routeHoursInCurrentMonth =
                getRouteHoursInCurrentMonth(
                        order.getRoute().getDuration(), getRestHoursOfMonth());
        int routeHoursInCurrentMonthForOne =
                routeHoursInCurrentMonth / order.getDrivers().size();
        return order.getDrivers()
                .stream()
                .allMatch(driver -> isDriverWithEnoughWorkingTime(
                        routeHoursInCurrentMonthForOne, driver));
    }

    /**
     * Checks that driver has enough working time.
     *
     * @param routeHoursInCurrentMonthForOne routeHoursInCurrentMonthForOne
     * @param driver                         driver
     * @return has or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isDriverWithEnoughWorkingTime(int routeHoursInCurrentMonthForOne, Driver driver) {
        int expectedTotalWorkingTime = routeHoursInCurrentMonthForOne + driver.getHours();
        if (expectedTotalWorkingTime > MagicConstants.WORKING_TIME_OF_MONTH) {
            throw new DriversWithNotEnoughWorkingTimeServiceException(
                    ORDER_DRIVERS_WITH_NOT_ENOUGH_WORKING_TIME);
        } else {
            return true;
        }
    }


    /**
     * Gets whole trip time if time to end of the month is more than trip time
     * or time to the end in hours otherwise.
     *
     * @param routeTime route time
     * @param restTime  rest time to end of month in hours
     * @return hours
     */
    @Transactional(propagation = SUPPORTS)
    private int getRouteHoursInCurrentMonth(int routeTime, int restTime) {
        return routeTime > restTime ? restTime : routeTime;
    }

    /**
     * Gets rest hours of month from current date.
     *
     * @return hours
     */
    @Transactional(propagation = SUPPORTS)
    private int getRestHoursOfMonth() {
        int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentHourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (lastDayOfMonth - currentDayOfMonth) * 24 + currentHourOfDay;
    }

    /**
     * Checks that truck has enough capacity.
     *
     * @param order order
     * @return is suitable truck or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasTruckWithEnoughCapacity(Order order) {
        double currentWeight = MagicConstants.DOUBLE_ZERO;
        for (String city : order.getRoute().getCities()) {
            for (Cargo cargo : order.getCargoes()) {
                currentWeight += getWeightDeltaByCity(city, cargo);
            }
            if (currentWeight > order.getTruck().getCapacity()) {
                throw new TruckWithNotEnoughCapacityServiceException(
                        ORDER_TRUCK_WITH_NOT_ENOUGH_CAPACITY);
            }
        }
        return true;
    }

    /**
     * Returns weights difference related to cargo in the given city.
     *
     * @param city  city
     * @param cargo cargo
     * @return delta weight
     */
    @Transactional(propagation = SUPPORTS)
    private double getWeightDeltaByCity(String city, Cargo cargo) {
        double delta = MagicConstants.DOUBLE_ZERO;
        delta += city.equalsIgnoreCase(cargo.getDepartureCity())
                ? cargo.getWeight()
                : MagicConstants.DOUBLE_ZERO;
        delta -= city.equalsIgnoreCase(cargo.getArrivalCity())
                ? cargo.getWeight()
                : MagicConstants.DOUBLE_ZERO;
        return delta;
    }

    /**
     * Checks that all cargoes have right departure and arrival order.
     *
     * @param order order
     * @return order is right or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasShippingAndDeliveringRightOrder(Order order) {
        if (order.getCargoes()
                .stream()
                .allMatch(cargo -> isCargoWithShippingAndDeliveringRightOrder(
                        cargo, order.getRoute().getCities()))) {
            return true;
        } else {
            throw new WrongOrderDepartureAndArrivalServiceException(
                    ORDER_WRONG_DEPARTURE_AND_ARRIVAL_ORDER);
        }
    }

    /**
     * Checks that cargo departure and arrival points match with route points order.
     *
     * @param cargo  cargo
     * @param cities route points
     * @return match or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isCargoWithShippingAndDeliveringRightOrder(Cargo cargo, List<String> cities) {
        int departureRoutePointNumber = 0;
        int arrivalRoutePointNumber = 0;
        for (String city : cities) {
            if (city.equalsIgnoreCase(cargo.getDepartureCity())) {
                departureRoutePointNumber = cities.indexOf(cargo.getDepartureCity());
            } else if (city.equalsIgnoreCase(cargo.getArrivalCity())) {
                arrivalRoutePointNumber = cities.indexOf(cargo.getArrivalCity());
            }
        }
        return departureRoutePointNumber != 0
                && arrivalRoutePointNumber != 0
                && departureRoutePointNumber < arrivalRoutePointNumber;
    }

    /**
     * Checks that truck and all drivers have different locations.
     *
     * @param order order
     * @return have same locations or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasSameLocationsTruckAndDrivers(Order order) {
        if (order.getDrivers()
                .stream()
                .allMatch(driver -> driver.getCity().equalsIgnoreCase(order.getTruck().getCity()))) {
            return true;
        } else {
            throw new DifferentLocationsTruckAndDriversServiceException(
                    ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_DRIVERS);
        }
    }

    /**
     * Checks that truck and start point of route have same locations.
     *
     * @param order order
     * @return have same locations or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasSameLocationsTruckAndRoute(Order order) {
        if (order.getTruck().getCity()
                .equalsIgnoreCase(order.getRoute().getCities().get(0))) {
            return true;
        } else {
            throw new DifferentLocationsTruckAndRouteServiceException(
                    ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_ROUTE);
        }
    }


    /**
     * Checks that drivers count is less than truck may take.
     *
     * @param order order
     * @return drivers count is less or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasNotTooManyDrivers(Order order) {
        if (order.getDrivers().size() <= order.getTruck().getPeople()) {
            return true;
        } else {
            throw new TruckHasTooManyDriversServiceException(ORDER_TOO_MANY_DRIVERS);
        }
    }

    /**
     * Checks that all drivers have status is FREE.
     *
     * @param order order
     * @return all are free or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isFreeDrivers(Order order) {
        if (order.getDrivers()
                .stream()
                .allMatch(driver -> driver.getStatus() == DriverStatus.FREE)) {
            return true;
        } else {
            throw new DriversNotFreeServiceException(ORDER_DRIVERS_NOT_FREE);
        }
    }


    /**
     * Checks that all cargoes have status is PREPARED.
     *
     * @param order order
     * @return all are prepare or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isPreparedCargoes(Order order) {
        if (order.getCargoes()
                .stream()
                .allMatch(cargo -> cargo.getStatus() == CargoStatus.PREPARED)) {
            return true;
        } else {
            throw new CargoesNotPreparedServiceException(ORDER_CARGOES_NOT_PREPARE);
        }
    }

    /**
     * Checks that truck is active.
     *
     * @param order order
     * @return is active or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isActiveTruck(Order order) {
        if (order.getTruck().isActive()) {
            return true;
        } else {
            throw new TruckNotActiveServiceException(ORDER_TRUCK_INACTIVE);
        }
    }

    /**
     * Checks that truck is assigned to an order.
     *
     * @param order order
     * @return is assigned or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasTruck(Order order) {
        if (order.getTruck() != null) {
            return true;
        } else {
            throw new TruckNotAssignedServiceException(ORDER_WITHOUT_TRUCK);
        }
    }

    /**
     * Checks that route is assigned to an order.
     *
     * @param order order
     * @return is assigned or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasRoute(Order order) {
        if (order.getRoute() != null) {
            return true;
        } else {
            throw new RouteNotAssignedServiceException(ORDER_WITHOUT_ROUTE);
        }
    }

    /**
     * Checks that drivers are added to an order.
     *
     * @param order order
     * @return are added or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasDrivers(Order order) {
        if (!order.getDrivers().isEmpty()) {
            return true;
        } else {
            throw new DriversNotAddedServiceException(ORDER_WITHOUT_DRIVERS);
        }
    }

    /**
     * Checks that cargoes are added to an order.
     *
     * @param order order
     * @return are added or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean hasCargoes(Order order) {
        if (!order.getCargoes().isEmpty()) {
            return true;
        } else {
            throw new DriversNotAddedServiceException(ORDER_WITHOUT_CARGOES);
        }
    }

    /**
     * Checks whether order is ready to deleting or not in accordance to a business-logic.
     *
     * @param order order
     */
    @Override
    public void checkAndDelete(Order order) {
        if (isReadyToModifying(order)) getDao().delete(order);
    }

    /**
     * Gets an order by given status and truck.
     *
     * @param status order status
     * @param truck  truck
     * @return order
     */
    @Transactional(readOnly = true)
    @Override
    public Order getByStatusAndTruck(OrderStatus status, Truck truck) {
        if (truck == null) return null;
        Order order = null;
        try {
            order = orderDao.getByStatusAndTruck(status, truck);
        } catch (PersistenceException ignore) {
            /* NOP */
        }
        return order;
    }
}
