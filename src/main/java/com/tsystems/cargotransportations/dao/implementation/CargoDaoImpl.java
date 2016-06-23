package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.entity.Cargo;

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
    public List<Cargo> getFreeCargoes() {
        String query = "SELECT c FROM Cargo AS c WHERE c.status = 'PREPARED'";
        return getLazyEntityManager().createQuery(query, Cargo.class).getResultList();
    }
}
