package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Implements business-logic operations that bound with cargo.
 */
@Service("cargoService")
public class CargoServiceImpl implements CargoService {
    /**
     * Instance of implementation of DriverDao class.
     */
    @Autowired
    private CargoDao cargoDao;

    /**
     * Instance of implementation of Cargo class.
     */
    @Autowired
    private OrderDao orderDao;

    @Transactional(readOnly = true)
    @Override
    public Cargo getByNumber(int cargoNumber) {
        return cargoDao.getByNumber(cargoNumber);
    }

    @Transactional
    @Override
    public void deleteByNumber(int cargoNumber) {
        cargoDao.delete(cargoDao.getByNumber(cargoNumber));
    }

    @Transactional
    @Override
    public void changeByNumber(int cargoNumber, String name, double weight) {
        Cargo cargo = cargoDao.getByNumber(cargoNumber);
        cargo.setName(name);
        cargo.setWeight(weight);
        cargoDao.update(cargo);
    }

    @Transactional
    @Override
    public void createCargo(String name, double weight, String departureCity, String arrivalCity) {
        Cargo cargo = new Cargo();
        cargoDao.create(cargo);
        cargo.setName(name);
        cargo.setWeight(weight);
        cargo.setDepartureCity(departureCity);
        cargo.setArrivalCity(arrivalCity);
        cargo.setStatus(CargoStatus.PREPARED);
        cargo.setNumber(cargo.getId() + 1000);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cargo> getAllCargoes() {
        return cargoDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cargo> getSuitableCargoes() {
        List<Cargo> cargoes = cargoDao.getFreeCargoes();
        List<Order> orders = orderDao.getAll();
        Iterator<Cargo> cargoIterator = cargoes.iterator();
        while (cargoIterator.hasNext()) {
            Cargo cargo = cargoIterator.next();
            for (Order order : orders) {
                if (order.getCargoes().contains(cargo)) {
                    cargoIterator.remove();
                    break;
                }
            }
        }
        return cargoes;
    }
}
