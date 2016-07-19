package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

import static com.tsystems.cargotransportations.constants.ServiceMapping.ORDER_SERVICE;

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
