package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Truck;

public class TruckManager extends AbstractManager<Truck> {

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

    public void updateActive(int truckId, boolean active) {
        Truck truck = read(truckId);
        getEntityManager().getTransaction().begin();
        truck.setActive(active);
        getEntityManager().getTransaction().commit();
    }
}
