package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Cargo;
import cargotransportations.dao.entity.OrderEntry;
import cargotransportations.dao.util.CargoStatus;

import javax.persistence.EntityManager;

public class CargoManager extends AbstractManager<Cargo> {

    public CargoManager(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Cargo> getItemClass() {
        return Cargo.class;
    }

    public Cargo create(String name, double weight, OrderEntry orderEntry) {
        Cargo cargo = create();
        getEntityManager().getTransaction().begin();
        cargo.setName(name);
        cargo.setWeight(weight);
        cargo.setOrderEntry(orderEntry);
        cargo.setStatus(CargoStatus.PREPARED);
        getEntityManager().getTransaction().commit();
        return cargo;
    }
}
