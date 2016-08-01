package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.TruckDao;
import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

import static com.tsystems.cargotransportations.constants.ServiceMapper.TRUCK_SERVICE;

/**
 * Implements business-logic operations that bound with truck.
 */
@Transactional
@Service(TRUCK_SERVICE)
public class TruckServiceImpl extends GenericServiceImpl<Truck> implements TruckService {

    /**
     * Gets an instance of dao implementation in this service.
     * @return an instance of dao implementation
     */
    @Override
    GenericDao<Truck> getDao() {
        return truckDao;
    }

    /**
     * Instance of implementation of TruckDao class.
     */
    @Autowired
    private TruckDao truckDao;

    /**
     * Gets truck by given number.
     * @param number truck number
     * @return truck
     */
    @Transactional(readOnly = true)
    @Override
    public Truck getByNumber(String number) {
        Truck truck = null;
        try {
            truck = truckDao.getByNumber(number);
        } catch (PersistenceException ignore) {
            /* NOP */
        }
        return truck;
    }

}
