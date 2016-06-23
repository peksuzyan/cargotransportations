package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;

import java.util.List;

/**
 * Specific DAO interface for order entity.
 * Contains specific operations over order entity.
 */
public interface OrderDao extends GenericDao<Order> {
    /**
     * Gets order by number.
     * @param number number
     * @return order
     */
    Order getByNumber(int number);

    /**
     * Gets orders with given status.
     * @param status status
     * @return orders list
     */
    List<Order> getAllByStatus(OrderStatus status);
}
