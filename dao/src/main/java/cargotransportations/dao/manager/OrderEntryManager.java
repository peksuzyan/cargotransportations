package cargotransportations.dao.manager;

import cargotransportations.dao.entity.OrderEntry;
import cargotransportations.dao.entity.Order;

public class OrderEntryManager extends AbstractManager<OrderEntry> {

    @Override
    public Class<OrderEntry> getItemClass() {
        return OrderEntry.class;
    }

    public OrderEntry create(String departureCity, String arrivalCity, Order order) {
        OrderEntry orderEntry = create();
        getEntityManager().getTransaction().begin();
        orderEntry.setDepartureCity(departureCity);
        orderEntry.setArrivalCity(arrivalCity);
        orderEntry.setOrder(order);
        getEntityManager().getTransaction().commit();
        return orderEntry;
    }

    public void update(int orderEntryId, String departureCity, String arrivalCity) {
        OrderEntry orderEntry = read(orderEntryId);
        getEntityManager().getTransaction().begin();
        orderEntry.setDepartureCity(departureCity);
        orderEntry.setArrivalCity(arrivalCity);
        getEntityManager().getTransaction().commit();
    }
}
