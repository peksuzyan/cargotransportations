package com.tsystems.cargotransportations.service.abstracts;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;

import java.util.List;

/**
 * Contains business-logic operations that bound with cargo.
 */
public interface CargoService {
    /**
     * Gets cargo by number.
     * @param cargoNumber cargoNumber
     * @return cargo
     */
    Cargo getByNumber(int cargoNumber);

    /**
     * Deletes cargo by number.
     * @param cargoNumber cargo number
     */
    void deleteByNumber(int cargoNumber);

    /**
     * Changes some fields of a cargo by number.
     * @param cargoNumber cargo number
     * @param name name for users
     * @param weight weight of a cargo
     */
    void changeByNumber(int cargoNumber, String name, double weight);

    /**
     * Creates a new cargo with passed values.
     * @param name name for users
     * @param weight weight of a cargo
     * @param departureCity departure city
     * @param arrivalCity arrival city
     */
    void createCargo(String name, double weight, String departureCity, String arrivalCity);

    /**
     * Gets a list of all existing cargoes.
     * @return cargoes list
     */
    List<Cargo> getAllCargoes();

    /**
     * Gets all cargoes that have given status.
     * @return cargoes list
     */
    List<Cargo> getAllByStatus(CargoStatus status);
}
