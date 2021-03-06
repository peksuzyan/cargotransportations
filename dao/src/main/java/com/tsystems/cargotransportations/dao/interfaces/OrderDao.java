package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;
import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Specific DAO interface for order entity.
 * Contains specific operations over order entity.
 */
public interface OrderDao extends GenericDao<Order> {

    /**
     * Gets orders with given status.
     * @param status status
     * @return orders list
     */
    List<Order> getAllByStatus(OrderStatus status);

    /**
     * Gets an order by given status and truck.
     * @param status order status
     * @param truck truck
     * @return order
     */
    Order getByStatusAndTruck(OrderStatus status, Truck truck);

}
