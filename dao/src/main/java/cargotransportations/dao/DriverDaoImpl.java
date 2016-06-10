package cargotransportations.dao;

import cargotransportations.entity.Driver;

public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao {

    public DriverDaoImpl() {
        super(Driver.class);
    }
}
