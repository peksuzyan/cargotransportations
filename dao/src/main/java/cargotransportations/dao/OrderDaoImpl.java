package cargotransportations.dao;

import cargotransportations.entity.Order;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }
}
