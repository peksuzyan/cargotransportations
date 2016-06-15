package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Order;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    public Order getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Order.class.getSimpleName(), number);
        List<Order> orders = getLazyEntityManager().createQuery(query, Order.class).getResultList();
        return orders.size() != 0 ? orders.get(0) : null;
    }
}
