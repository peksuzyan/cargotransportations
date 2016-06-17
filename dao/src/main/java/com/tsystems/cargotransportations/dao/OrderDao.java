package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Order;

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
}
