package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Cargo;

import java.util.List;

/**
 * Contains business-logic operations that bound with cargo.
 */
public interface CargoService extends GenericService<Cargo> {

    /**
     * Checks whether cargo is ready to modifying or not in accordance to a business-logic.
     * @param cargo cargo
     */
    boolean isReadyToModifying(Cargo cargo);

    /**
     * Checks whether cargo is ready to deleting or not in accordance to a business-logic.
     * @param cargo cargo
     */
    void checkAndDelete(Cargo cargo);

    /**
     * Checks whether cargo is ready to updating or not in accordance to a business-logic.
     * @param cargo cargo
     */
    void checkAndUpdate(Cargo cargo);

    /**
     * Gets all cargoes that have given status.
     * @return cargoes list
     */
    List<Cargo> getSuitableCargoes();

}
