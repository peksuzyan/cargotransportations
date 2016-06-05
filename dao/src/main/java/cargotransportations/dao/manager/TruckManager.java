package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Truck;

import javax.persistence.EntityManager;

public class TruckManager extends AbstractManager<Truck> {

    public TruckManager(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Truck> getItemClass() {
        return Truck.class;
    }

    public Truck create(String number, int people, double capacity, String city) {
        Truck truck = create();
        getEntityManager().getTransaction().begin();
        truck.setNumber(number);
        truck.setPeople(people);
        truck.setCapacity(capacity);
        truck.setCity(city);
        truck.setActive(true);
        getEntityManager().getTransaction().commit();
        return truck;
    }
}
