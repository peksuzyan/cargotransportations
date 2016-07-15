package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.TruckDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;

import com.tsystems.cargotransportations.constants.MagicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.cargotransportations.constants.ServiceMapping.TRUCK_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with truck.
 */
@Transactional
@Service(TRUCK_SERVICE)
public class TruckServiceImpl extends GenericServiceImpl<Truck> implements TruckService {

    @Override
    GenericDao<Truck> getDao() {
        return truckDao;
    }

    /**
     * Instance of implementation of TruckDao class.
     */
    @Autowired
    private TruckDao truckDao;

    @Transactional(readOnly = true)
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
    @Transactional(propagation = SUPPORTS)
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
    @Transactional(propagation = SUPPORTS)
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
    @Transactional(propagation = SUPPORTS)
    private double getWeightDeltaByCity(String city, Cargo cargo) {
        double delta = MagicConstants.DOUBLE_ZERO;
        delta += city.equalsIgnoreCase(cargo.getDepartureCity()) ? cargo.getWeight() : MagicConstants.DOUBLE_ZERO;
        delta -= city.equalsIgnoreCase(cargo.getArrivalCity()) ? cargo.getWeight() : MagicConstants.DOUBLE_ZERO;
        return delta;
    }

}
