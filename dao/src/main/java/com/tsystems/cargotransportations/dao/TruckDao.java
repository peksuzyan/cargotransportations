package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

public interface TruckDao extends GenericDao<Truck> {
    Truck getByNumber(String number);
    List<Truck> getAppropriatedTrucks();
}
