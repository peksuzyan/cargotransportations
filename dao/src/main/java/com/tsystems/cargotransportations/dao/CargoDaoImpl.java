package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;

import java.util.List;

/**
 * Specific DAO implementation for cargoes management.
 */
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    public CargoDaoImpl() {
        super(Cargo.class);
    }

    @Override
    public Cargo getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Cargo.class.getSimpleName(), number);
        List<Cargo> cargoes = getLazyEntityManager().createQuery(query, Cargo.class).getResultList();
        return cargoes.size() != 0 ? cargoes.get(0) : null;
    }

    @Override
    public List<Cargo> getAllByStatus(CargoStatus status) {
        String query = String.format(
                "SELECT c FROM Cargo c WHERE c.status = '%s'", status);
        return getLazyEntityManager().createQuery(query, Cargo.class).getResultList();
    }
}
