package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import com.tsystems.cargotransportations.exception.CargoIsDeliveredServiceException;
import com.tsystems.cargotransportations.exception.CargoIsShippedServiceException;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tsystems.cargotransportations.constants.ExceptionCodes.CARGO_ALREADY_DELIVERED;
import static com.tsystems.cargotransportations.constants.ExceptionCodes.CARGO_ALREADY_SHIPPED;
import static com.tsystems.cargotransportations.constants.ServiceMapper.CARGO_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with cargo.
 */
@Transactional
@Service(CARGO_SERVICE)
public class CargoServiceImpl extends GenericServiceImpl<Cargo> implements CargoService {

    /**
     * Gets an instance of dao implementation in this service.
     * @return an instance of dao implementation
     */
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
     * Changes cargo status by id.
     * @param id cargo id.
     * @return is changed or not
     */
    @Override
    public boolean changeStatusById(int id) {
        Cargo cargo = cargoDao.read(id);
        if (cargo == null) return false;
        cargo.setStatus(CargoStatus.DELIVERED);
        cargoDao.update(cargo);
        return true;
    }

    /**
     * Checks whether cargo is ready to modifying or not in accordance to a business-logic.
     * @param cargo cargo
     */
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

    /**
     * Checks whether cargo is ready to deleting or not in accordance to a business-logic.
     * @param cargo cargo
     */
    @Override
    public void checkAndDelete(Cargo cargo) {
        if (isReadyToModifying(cargo)) getDao().delete(cargo);
    }

    /**
     * Checks whether cargo is ready to updating or not in accordance to a business-logic.
     * @param cargo cargo
     */
    @Override
    public void checkAndUpdate(Cargo cargo) {
        if (isReadyToModifying(cargo)) getDao().update(cargo);
    }

}
