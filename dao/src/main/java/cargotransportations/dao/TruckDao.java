package cargotransportations.dao;

import cargotransportations.entity.Truck;

import java.util.List;

public interface TruckDao extends GenericDao<Truck> {
    List<Truck> getAppropriatedTrucks();
}
