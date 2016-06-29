package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.dao.interfaces.TruckDao;
import com.tsystems.cargotransportations.dao.implementation.TruckDaoImpl;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;

import com.tsystems.cargotransportations.constants.MagicConstants;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.cargotransportations.constants.MagicConstants.DOUBLE_ZERO;

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
    public List<Truck> getSuitableTrucksByOrder(Order order) {
        List<Truck> suitableTrucks = new ArrayList<>();
        if (order.getRoute() != null) {
            List<Truck> trucks = truckDao.getActiveAndFreeTrucks();
            for (Truck truck : trucks) {
                if (isSameLocationCity(truck, order.getRoute())
                        && isTruckWithEnoughCapacity(truck, order.getRoute().getCities(), order.getCargoes())) {
                    suitableTrucks.add(truck);
                }
            }
        }
        return suitableTrucks;
    }

    /**
     * Returns does truck location equals relate to route start or not.
     * @param truck truck
     * @param route route
     * @return locations equals or not
     */
    private boolean isSameLocationCity(Truck truck, Route route) {
        return truck.getCity().equalsIgnoreCase(route.getCities().get(0));
    }

    /**
     * Returns true when truck is suitable for order performing by route or false otherwise.
     * @param truck truck
     * @param cities list of route points of an order
     * @param cargoes cargoes list of an order
     * @return is suitable truck or not
     */
    private boolean isTruckWithEnoughCapacity(
            Truck truck, List<String> cities, List<Cargo> cargoes) {
        double currentWeight = MagicConstants.DOUBLE_ZERO;
        for (String city : cities) {
            for (Cargo cargo : cargoes) {
                currentWeight += getWeightDeltaByCity(city, cargo);
            }
            if (currentWeight > truck.getCapacity()) return false;
        }
        return true;
    }

    /**
     * Returns weights difference related to cargo in the given city.
     * @param city city
     * @param cargo cargo
     * @return delta weight
     */
    private double getWeightDeltaByCity(String city, Cargo cargo) {
        double delta = MagicConstants.DOUBLE_ZERO;
        delta += city.equalsIgnoreCase(cargo.getDepartureCity()) ? cargo.getWeight() : MagicConstants.DOUBLE_ZERO;
        delta -= city.equalsIgnoreCase(cargo.getArrivalCity()) ? cargo.getWeight() : MagicConstants.DOUBLE_ZERO;
        return delta;
    }
}
