package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Contains business-logic operations that bound with truck.
 */
public interface TruckService {
    /**
     * Gets truck by number.
     * @param number number
     * @return truck
     */
    Truck getByNumber(String number);

    /**
     * Deletes truck by number.
     * @param number number
     */
    void deleteByNumber(String number);

    /**
     * Changes some fields of a truck by number.
     * @param number number
     * @param people max count of drivers
     * @param active active or not active
     * @param capacity max capacity
     */
    void changeByNumber(String number, int people, boolean active, double capacity);

    /**
     * Creates a new truck with passed values.
     * @param number number
     * @param people max count of drivers
     * @param active active or not active
     * @param capacity max capacity
     * @param city current city
     */
    void createTruck(String number, int people, boolean active, double capacity, String city);

    /**
     * Gets a list of all existing trucks.
     * @return trucks list
     */
    List<Truck> getAllTrucks();

    /**
     * Gets all trucks that suitable for assigning of the order given order number.
     * @param orderNumber order number
     * @return trucks list
     */
    List<Truck> getSuitableTrucksByOrder(int orderNumber);
}
