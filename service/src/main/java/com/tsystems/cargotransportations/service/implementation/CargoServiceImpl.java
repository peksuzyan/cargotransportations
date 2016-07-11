package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.exception.CargoAlreadyDeliveredBOException;
import com.tsystems.cargotransportations.exception.CargoAlreadyShippedBOException;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static com.tsystems.cargotransportations.constants.BOConstants.CARGO_ALREADY_DELIVERED;
import static com.tsystems.cargotransportations.constants.BOConstants.CARGO_ALREADY_SHIPPED;

/**
 * Implements business-logic operations that bound with cargo.
 */
@Transactional
@Service("cargoService")
public class CargoServiceImpl extends GenericServiceImpl<Cargo> implements CargoService {

    @Override
    GenericDao<Cargo> getDao() {
        return cargoDao;
    }
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

    /**
     * Checks whether cargo is ready to modifying or not in accordance to a business-logic
     * @param cargo cargo
     * @return is ready or not
     */
    private boolean isReadyToModifying(Cargo cargo) {
        if (cargo.getStatus() == CargoStatus.SHIPPED) {
            throw new CargoAlreadyShippedBOException(CARGO_ALREADY_SHIPPED);
        }
        if (cargo.getStatus() == CargoStatus.DELIVERED) {
            throw new CargoAlreadyDeliveredBOException(CARGO_ALREADY_DELIVERED);
        }
        return true;
    }

    @Override
    public void checkAndDelete(Cargo cargo) {
        if (isReadyToModifying(cargo)) getDao().delete(cargo);
    }

    @Override
    public void checkAndUpdate(Cargo cargo) {
        if (isReadyToModifying(cargo)) getDao().update(cargo);
    }

    @Override
    public void createCargo(Cargo cargo) {
        cargoDao.create(cargo);
    }

    @Override
    public Cargo getByNumber(int cargoNumber) {
        return cargoDao.getByNumber(cargoNumber);
    }

    @Override
    public void deleteByNumber(int cargoNumber) {
        cargoDao.delete(cargoDao.getByNumber(cargoNumber));
    }

    @Override
    public void changeByNumber(int cargoNumber, String name, double weight) {
        Cargo cargo = cargoDao.getByNumber(cargoNumber);
        cargo.setName(name);
        cargo.setWeight(weight);
        cargoDao.update(cargo);
    }

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
