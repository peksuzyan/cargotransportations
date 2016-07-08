package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CargoDaoImplTest {

    static {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:test-dao-context.xml");
        ctx.refresh();
    }

    @Autowired
    private CargoDao cargoDao;

    @Transactional
    public void createInTransaction(Cargo cargo) {
        cargoDao.create(cargo);
    }

    @Transactional(readOnly = true)
    public Cargo readInTransaction(int id) {
        return cargoDao.read(id);
    }

    @Test
    public void createAndRead() {
        Cargo cargoOut = new Cargo();

        cargoOut.setName("orange");
        cargoOut.setWeight(23.1);
        cargoOut.setDepartureCity("Moscow");
        cargoOut.setArrivalCity("Saint-Petersburg");
        cargoOut.setStatus(CargoStatus.SHIPPED);

        cargoDao.create(cargoOut);
        Cargo cargoIn = cargoDao.read(cargoOut.getId());

        Assert.assertTrue(cargoOut.equals(cargoIn));
    }
}
