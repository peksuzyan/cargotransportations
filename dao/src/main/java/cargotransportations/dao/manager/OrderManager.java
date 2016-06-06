package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Driver;
import cargotransportations.dao.entity.Order;
import cargotransportations.dao.entity.OrderEntry;

import java.util.ArrayList;

public class OrderManager extends AbstractManager<Order> {

    @Override
    public Class<Order> getItemClass() {
        return Order.class;
    }

    public void addOrderEntry(int orderId, OrderEntry orderEntry) {
        Order order = read(orderId);
        getEntityManager().getTransaction().begin();
        if (order.getOrderEntries() == null) {
            order.setOrderEntries(new ArrayList<OrderEntry>());
        }
        order.getOrderEntries().add(orderEntry);
        getEntityManager().getTransaction().commit();
    }

    public void addDriver(int orderId, Driver driver) {
        Order order = read(orderId);
        getEntityManager().getTransaction().begin();
        if (order.getDrivers() == null) {
            order.setDrivers(new ArrayList<Driver>());
        }
        order.getDrivers().add(driver);
        getEntityManager().getTransaction().commit();
    }
}
