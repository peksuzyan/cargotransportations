package cargotransportations.dao;

import cargotransportations.dao.entity.Cargo;
import cargotransportations.dao.entity.OrderEntry;
import cargotransportations.dao.entity.Order;
import cargotransportations.dao.manager.OrderEntryManager;
import cargotransportations.dao.manager.CargoManager;
import cargotransportations.dao.manager.OrderManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Simulator {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cargotransportations");

    public static void main(String[] args) {
        /*
        CargoManager cargoManager = new CargoManager(emf.createEntityManager());
        Cargo cargo = cargoManager.create();
        System.out.println(String.format(
                "id: %d, name: %s, weight: %e, status: %s, cargoEntry: %s",
                cargo.getId(),
                cargo.getName(),
                cargo.getWeight(),
                cargo.getStatus(),
                cargo.getOrderEntry())
        );
        */
        createCascadeOrder("snickers chocolate", 12.1, "Moscow", "Saint-Petersburg");
    }

    private static void createCascadeOrder(String name, double weight, String departureCity, String arrivalCity) {
        /*
        EntityManager entityManager = emf.createEntityManager();
        CargoManager cargoManager = new CargoManager(entityManager);
        OrderEntryManager orderEntryManager = new OrderEntryManager(entityManager);
        OrderManager orderManager = new OrderManager(entityManager);

        Order order = orderManager.create();
        OrderEntry orderEntry = orderEntryManager.create(departureCity, arrivalCity, order);
        Cargo cargo = cargoManager.create(name, weight, orderEntry);
        orderEntry.setCargo(cargo);
        if (order.getCargoEntries() != null) {
            order.getCargoEntries().add(orderEntry);
        } else {
            order.setCargoEntries(new ArrayList<OrderEntry>());
            order.getCargoEntries().add(orderEntry);
        }
        */
    }
}
