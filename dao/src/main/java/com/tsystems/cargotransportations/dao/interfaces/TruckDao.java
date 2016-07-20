package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Specific DAO interface for truck entity.
 * Contains specific operations over truck entity.
 */
public interface TruckDao extends GenericDao<Truck> {

    /**
     * Gets a list with active and free trucks.
     * @return trucks list
     */
    List<Truck> getActiveAndFreeTrucks();

    /**
     * Gets truck by given number.
     * @param number truck number
     * @return truck
     */
    Truck getByNumber(String number);
}
