package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;

import java.util.List;

/**
 * Specific DAO implementation for orders management.
 */
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Order getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Order.class.getSimpleName(), number);
        List<Order> orders = getLazyEntityManager().createQuery(query, Order.class).getResultList();
        return orders.size() != 0 ? orders.get(0) : null;
    }

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        String query = String.format(
                "FROM %s WHERE status = '%s'", Order.class.getSimpleName(), status);
        return getLazyEntityManager().createQuery(query, Order.class).getResultList();
    }
}
