package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.exception.CargoIsDeliveredServiceException;
import com.tsystems.cargotransportations.exception.CargoIsShippedServiceException;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static com.tsystems.cargotransportations.constants.ServiceConstants.CARGO_ALREADY_DELIVERED;
import static com.tsystems.cargotransportations.constants.ServiceConstants.CARGO_ALREADY_SHIPPED;
import static com.tsystems.cargotransportations.constants.ServiceMapping.CARGO_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with cargo.
 */
@Transactional
@Service(CARGO_SERVICE)
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

    @Transactional(propagation = SUPPORTS)
    @Override
    public boolean isReadyToModifying(Cargo cargo) {
        if (cargo.getStatus() == CargoStatus.SHIPPED) {
            throw new CargoIsShippedServiceException(CARGO_ALREADY_SHIPPED);
        }
        if (cargo.getStatus() == CargoStatus.DELIVERED) {
            throw new CargoIsDeliveredServiceException(CARGO_ALREADY_DELIVERED);
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

    @Transactional(readOnly = true)
    @Override
    public List<Cargo> getSuitableCargoes() {
        List<Cargo> cargoes = cargoDao.getCargoesByStatus(CargoStatus.PREPARED);
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
