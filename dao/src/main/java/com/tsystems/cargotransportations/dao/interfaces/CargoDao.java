package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;

import java.util.List;

/**
 * Specific DAO interface for cargo entity.
 * Contains specific operations over cargo entity.
 */
public interface CargoDao extends GenericDao<Cargo> {

    /**
     * Gets all cargoes by a given status.
     * @param status cargo status
     * @return cargoes list
     */
    List<Cargo> getCargoesByStatus(CargoStatus status);

}
