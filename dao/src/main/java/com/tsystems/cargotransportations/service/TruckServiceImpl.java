package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.dao.DaoUtils;
import com.tsystems.cargotransportations.dao.TruckDao;
import com.tsystems.cargotransportations.dao.TruckDaoImpl;
import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Implements business-logic operations that bound with truck.
 */
public class TruckServiceImpl implements TruckService {
    /**
     * Instance of implementation of TruckDao class.
     */
    private TruckDao truckDao = new TruckDaoImpl();

    @Override
    public Truck getByNumber(String number) {
        return truckDao.getByNumber(number);
    }

    @Override
    public void deleteByNumber(String number) {
        DaoUtils.executeInTransaction(() -> {
            truckDao.delete(truckDao.getByNumber(number));
        });
    }

    @Override
    public void changeByNumber(String number, int people, boolean active, double capacity) {
        DaoUtils.executeInTransaction(() -> {
            Truck truck = truckDao.getByNumber(number);
            truck.setPeople(people);
            truck.setActive(active);
            truck.setCapacity(capacity);
            truckDao.update(truck);
        });
    }

    @Override
    public void createTruck(String number, int people, boolean active, double capacity, String city) {
        DaoUtils.executeInTransaction(() -> {
            Truck truck = new Truck();
            truckDao.create(truck);
            truck.setNumber(number);
            truck.setPeople(people);
            truck.setActive(active);
            truck.setCapacity(capacity);
            truck.setCity(city);
        });
    }

    @Override
    public List<Truck> getAllTrucks() {
        return truckDao.getAll();
    }

    @Override
    public List<Truck> getSuitableTrucksByOrder(int orderNumber) {
        // It's need to implement.
        return null;
    }
}
