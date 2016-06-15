package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Order;

public interface OrderDao extends GenericDao<Order> {
    Order getByNumber(int number);
}
