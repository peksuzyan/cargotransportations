package cargotransportations.service;

import cargotransportations.entity.Driver;
import cargotransportations.util.DriverStatus;

import java.util.List;

public interface DriverService {
    void createDriver(String firstName, String lastName, String city);
    void removeDriver(int id);
    void changeDriver(int id, String firstName, String lastName, String city,
                      int hours, DriverStatus status, int truckId, int orderId);
    List<Driver> getAllDrivers();
}
