package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Cargo;

import java.util.List;

public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    public CargoDaoImpl() {
        super(Cargo.class);
    }

    public Cargo getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Cargo.class.getSimpleName(), number);
        List<Cargo> cargoes = getLazyEntityManager().createQuery(query, Cargo.class).getResultList();
        return cargoes.size() != 0 ? cargoes.get(0) : null;
    }
}