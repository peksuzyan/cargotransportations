package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;

import java.util.List;

/**
 * Specific DAO interface for cargo entity.
 * Contains specific operations over cargo entity.
 */
public interface CargoDao extends GenericDao<Cargo> {
    /**
     * Gets cargo by number.
     * @param number number
     * @return cargo
     */
    Cargo getByNumber(int number);

    /**
     * Gets all cargoes by a given status.
     * @param status status
     * @return cargoes list
     */
    List<Cargo> getAllByStatus(CargoStatus status);
}
