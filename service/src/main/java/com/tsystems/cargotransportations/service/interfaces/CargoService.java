package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Cargo;

import java.util.List;

/**
 * Contains business-logic operations that bound with cargo.
 */
public interface CargoService extends GenericService<Cargo> {

    @Deprecated
    void createCargo(Cargo cargo);
    /**
     * Gets cargo by number.
     * @param cargoNumber cargoNumber
     * @return cargo
     */
    @Deprecated
    Cargo getByNumber(int cargoNumber);

    /**
     * Deletes cargo by number.
     * @param cargoNumber cargo number
     */
    @Deprecated
    void deleteByNumber(int cargoNumber);

    /**
     * Changes some fields of a cargo by number.
     * @param cargoNumber cargo number
     * @param name name for users
     * @param weight weight of a cargo
     */
    @Deprecated
    void changeByNumber(int cargoNumber, String name, double weight);

    /**
     * Creates a new cargo with passed values.
     * @param name name for users
     * @param weight weight of a cargo
     * @param departureCity departure city
     * @param arrivalCity arrival city
     */
    @Deprecated
    void createCargo(String name, double weight, String departureCity, String arrivalCity);

    /**
     * Gets a list of all existing cargoes.
     * @return cargoes list
     */
    @Deprecated
    List<Cargo> getAllCargoes();

    /**
     * Gets all cargoes that have given status.
     * @return cargoes list
     */
    List<Cargo> getSuitableCargoes();
}
