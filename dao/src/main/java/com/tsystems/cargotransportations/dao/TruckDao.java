package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Specific DAO interface for truck entity.
 * Contains specific operations over truck entity.
 */
public interface TruckDao extends GenericDao<Truck> {
    /**
     * Gets driver by number.
     * @param number number
     * @return truck
     */
    Truck getByNumber(String number);

    /**
     * Gets a list with active and free trucks.
     * @return trucks list
     */
    List<Truck> getActiveAndFreeTrucks();
}
