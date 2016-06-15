package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Cargo;

/**
 *
 */
interface CargoDao extends GenericDao<Cargo> {
    /**
     * Gets cargo by number.
     * @param number number
     * @return cargo found
     */
    Cargo getByNumber(int number);
}
