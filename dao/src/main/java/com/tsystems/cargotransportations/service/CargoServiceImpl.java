package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.dao.CargoDao;
import com.tsystems.cargotransportations.dao.CargoDaoImpl;
import com.tsystems.cargotransportations.dao.DaoUtils;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;

import java.util.List;

/**
 * Implements business-logic operations that bound with cargo.
 */
public class CargoServiceImpl implements CargoService {
    /**
     * Instance of implementation of DriverDao class.
     */
    private CargoDao cargoDao = new CargoDaoImpl();

    @Override
    public Cargo getByNumber(int cargoNumber) {
        return cargoDao.getByNumber(cargoNumber);
    }

    @Override
    public void deleteByNumber(int cargoNumber) {
        DaoUtils.executeInTransaction(() -> {
            cargoDao.delete(cargoDao.getByNumber(cargoNumber));
        });
    }

    @Override
    public void changeByNumber(int cargoNumber, String name, double weight) {
        DaoUtils.executeInTransaction(() -> {
            Cargo cargo = cargoDao.getByNumber(cargoNumber);
            cargo.setName(name);
            cargo.setWeight(weight);
            cargoDao.update(cargo);
        });
    }

    @Override
    public void createCargo(String name, double weight, String departureCity, String arrivalCity) {
        DaoUtils.executeInTransaction(() -> {
            Cargo cargo = new Cargo();
            cargoDao.create(cargo);
            cargo.setName(name);
            cargo.setWeight(weight);
            cargo.setDepartureCity(departureCity);
            cargo.setArrivalCity(arrivalCity);
            cargo.setStatus(CargoStatus.PREPARED);
            cargo.setNumber(cargo.getId() + 1000);
        });
    }

    @Override
    public List<Cargo> getAllCargoes() {
        return cargoDao.getAll();
    }

    @Override
    public List<Cargo> getAllByStatus(CargoStatus status) {
        return cargoDao.getAllByStatus(status);
    }
}
