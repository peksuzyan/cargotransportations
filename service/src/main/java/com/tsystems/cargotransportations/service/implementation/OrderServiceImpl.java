package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.*;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

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
                && hasNotTooManyDrivers(order);
    }

    /**
     * Checks that drivers count is less than truck may take.
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
