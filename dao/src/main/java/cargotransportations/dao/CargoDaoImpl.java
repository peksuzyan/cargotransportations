package cargotransportations.dao;

import cargotransportations.entity.Cargo;

public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    public CargoDaoImpl() {
        super(Cargo.class);
    }
}
