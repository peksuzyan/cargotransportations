package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Truck;

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
}
