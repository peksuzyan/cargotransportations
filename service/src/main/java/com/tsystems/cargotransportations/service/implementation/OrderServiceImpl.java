package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.constants.values.MagicValues;
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

import static com.tsystems.cargotransportations.constants.codes.ExceptionCodes.*;
import static com.tsystems.cargotransportations.constants.mapping.ServiceMapper.ORDER_SERVICE;
import static com.tsystems.cargotransportations.constants.values.MagicValues.HOURS_IN_DAY;
import static com.tsystems.cargotransportations.constants.values.MagicValues.INDEX_OF_ABSENT;
import static com.tsystems.cargotransportations.constants.values.MagicValues.START_POINT_NUMBER;
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
     * Returns a list of routes by suitable conditions.
     *
     * @return routes list
     */
    @Override
    public List<Route> getSuitableRoutes() {
        return routeDao.getAll();
    }

    /**
     * Returns a truck of the order by a given driver.
     *
     * @param driver driver
     * @return truck
     */
    @Override
    public Truck getTruckByDriver(Driver driver) {
        return orderDao.getAllByStatus(OrderStatus.PERFORMING)
                .stream()
                .filter(order -> order.getDrivers().contains(driver))
                .findFirst()
                .orElseThrow(() -> new OrderNotExistServiceException(ORDER_NOT_EXIST))
                .getTruck();
    }

    /**
     * Creates order after filling of all needed fields.
     *
     * @param orderId orderId
     */
    @Override
    public void createOrder(int orderId) {
        Order order = orderDao.read(orderId);
        order.getDrivers()
                .stream()
                .forEach(driver -> {
                    driver.setStatus(DriverStatus.ASSIGNED);
                    driverDao.update(driver);
                });
        order.getCargoes()
                .stream()
                .forEach(cargo -> {
                    cargo.setStatus(CargoStatus.SHIPPED);
                    cargoDao.update(cargo);
                });
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
        return isPresent(order)
                && hasPreparedCargoes(order)
                && hasActiveTruck(order)
                && hasFreeDrivers(order)
                && hasFullRoute(order)
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
    public boolean hasDriversWithEnoughWorkingTime(Order order) {
        int routeHoursInCurrentMonth =
                getRouteHoursInCurrentMonth(
                        order.getRoute().getDuration(), getRestHoursOfMonth());
        int routeHoursInCurrentMonthForOne =
                routeHoursInCurrentMonth / order.getDrivers().size();
        return order.getDrivers().stream()
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
        if (expectedTotalWorkingTime > MagicValues.WORKING_TIME_OF_MONTH)
            throw new DriversWithNotEnoughWorkingTimeServiceException(
                    ORDER_DRIVERS_WITH_NOT_ENOUGH_WORKING_TIME);
        return true;
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
        return (lastDayOfMonth - currentDayOfMonth) * HOURS_IN_DAY + currentHourOfDay;
    }

    /**
     * Checks that truck has enough capacity.
     *
     * @param order order
     * @return is suitable truck or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasTruckWithEnoughCapacity(Order order) {
        double currentWeight = MagicValues.DOUBLE_ZERO;
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
        double delta = MagicValues.DOUBLE_ZERO;
        delta += city.equalsIgnoreCase(cargo.getDepartureCity())
                ? cargo.getWeight()
                : MagicValues.DOUBLE_ZERO;
        delta -= city.equalsIgnoreCase(cargo.getArrivalCity())
                ? cargo.getWeight()
                : MagicValues.DOUBLE_ZERO;
        return delta;
    }

    /**
     * Checks that all cargoes have right departure and arrival order.
     *
     * @param order order
     * @return order is right or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasShippingAndDeliveringRightOrder(Order order) {
        if (order.getCargoes().stream()
                .anyMatch(cargo -> !isCargoWithShippingAndDeliveringRightOrder(
                        cargo, order.getRoute().getCities())))
            throw new WrongOrderDepartureAndArrivalServiceException(
                    ORDER_WRONG_DEPARTURE_AND_ARRIVAL_ORDER);
        return true;
    }

    /**
     * Checks that cargo departure and arrival points match with route points order.
     *
     * @param cargo  cargo
     * @param cities route points
     * @return matched or not
     */
    @Transactional(propagation = SUPPORTS)
    private boolean isCargoWithShippingAndDeliveringRightOrder(Cargo cargo, List<String> cities) {
        int departureRoutePointNumber = cities.indexOf(cargo.getDepartureCity());
        int arrivalRoutePointNumber = cities.indexOf(cargo.getArrivalCity());
        return departureRoutePointNumber != INDEX_OF_ABSENT
                && arrivalRoutePointNumber != INDEX_OF_ABSENT
                && departureRoutePointNumber < arrivalRoutePointNumber;
    }

    /**
     * Checks that truck and all drivers have different locations.
     *
     * @param order order
     * @return have same locations or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasSameLocationsTruckAndDrivers(Order order) {
        if (order.getDrivers().stream()
                .anyMatch(driver -> !driver.getCity().equalsIgnoreCase(order.getTruck().getCity())))
            throw new DifferentLocationsTruckAndDriversServiceException(
                    ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_DRIVERS);
        return true;
    }

    /**
     * Checks that truck and start point of route have same locations.
     *
     * @param order order
     * @return have same locations or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasSameLocationsTruckAndRoute(Order order) {
        if (!order.getTruck().getCity()
                .equalsIgnoreCase(order.getRoute().getCities().get(START_POINT_NUMBER)))
            throw new DifferentLocationsTruckAndRouteServiceException(
                    ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_ROUTE);
        return true;
    }


    /**
     * Checks that drivers count is less than truck may take.
     *
     * @param order order
     * @return drivers count is less or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasNotTooManyDrivers(Order order) {
        if (order.getDrivers().size() > order.getTruck().getPeople())
            throw new TruckHasTooManyDriversServiceException(ORDER_TOO_MANY_DRIVERS);
        return true;
    }

    /**
     * Checks that a truck is active and assigned to an order.
     *
     * @param order order
     * @return is assigned or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasActiveTruck(Order order) {
        if (order.getTruck() == null)
            throw new TruckNotAssignedServiceException(ORDER_WITHOUT_TRUCK);
        if (!order.getTruck().isActive())
            throw new TruckNotActiveServiceException(ORDER_TRUCK_INACTIVE);
        return true;
    }

    /**
     * Checks that route is full and assigned to an order.
     *
     * @param order order
     * @return is assigned or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasFullRoute(Order order) {
        if (order.getRoute() == null)
            throw new RouteNotAssignedServiceException(ORDER_WITHOUT_ROUTE);
        if (order.getRoute().getCities() == null || order.getRoute().getCities().size() < 2)
            throw new RouteNotFullServiceException(ORDER_ROUTE_NOT_FULL);
        return true;
    }

    /**
     * Checks that drivers are added to an order.
     *
     * @param order order
     * @return are added or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasFreeDrivers(Order order) {
        if (order.getDrivers() == null || order.getDrivers().isEmpty())
            throw new DriversNotAddedServiceException(ORDER_WITHOUT_DRIVERS);
        if (order.getDrivers().stream()
                .anyMatch(driver -> driver.getStatus() != DriverStatus.FREE))
            throw new DriversNotFreeServiceException(ORDER_DRIVERS_NOT_FREE);
        return true;
    }

    /**
     * Checks that cargoes are prepared and added to an order.
     *
     * @param order order
     * @return are added or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean hasPreparedCargoes(Order order) {
        if (order.getCargoes() == null || order.getCargoes().isEmpty())
            throw new CargoesNotAddedServiceException(ORDER_WITHOUT_CARGOES);
        if (order.getCargoes().stream()
                .anyMatch(cargo -> cargo.getStatus() != CargoStatus.PREPARED))
            throw new CargoesNotPreparedServiceException(ORDER_CARGOES_NOT_PREPARE);
        return true;
    }

    /**
     * Checks that order is present.
     *
     * @param order order
     * @return is present or not
     */
    @Transactional(propagation = SUPPORTS)
    public boolean isPresent(Order order) {
        if (order == null)
            throw new OrderNotExistServiceException(ORDER_NOT_EXIST);
        return true;
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
