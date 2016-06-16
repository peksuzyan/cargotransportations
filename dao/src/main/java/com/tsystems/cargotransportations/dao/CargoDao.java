package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Cargo;

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
}
