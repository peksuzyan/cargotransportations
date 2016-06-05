package cargotransportations.dao.manager;

import cargotransportations.dao.entity.OrderEntry;
import cargotransportations.dao.entity.Order;

import javax.persistence.EntityManager;

public class OrderEntryManager extends AbstractManager<OrderEntry> {

    public OrderEntryManager(EntityManager entityManager) {
        super(entityManager);
    }

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
}
