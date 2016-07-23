package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.OrderIsDoneServiceException;
import com.tsystems.cargotransportations.exception.OrderIsPerformingServiceException;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

import static com.tsystems.cargotransportations.constants.ServiceConstants.ORDER_IS_DONE;
import static com.tsystems.cargotransportations.constants.ServiceConstants.ORDER_IS_PERFORMING;
import static com.tsystems.cargotransportations.constants.ServiceMapping.ORDER_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with order.
 */
@Transactional
@Service(ORDER_SERVICE)
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {

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
     * Checks whether order is ready to modifying or not in accordance to a business-logic.
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
     * Checks whether order is ready to deleting or not in accordance to a business-logic.
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
