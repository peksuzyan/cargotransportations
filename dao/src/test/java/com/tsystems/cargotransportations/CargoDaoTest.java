package com.tsystems.cargotransportations;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class CargoDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createCargo() {
        Cargo cargo = new Cargo();
        cargo.setName("orange");
        cargo.setWeight(23.1);
        cargo.setDepartureCity("Moscow");
        cargo.setArrivalCity("Saint-Petersburg");
        cargo.setStatus(CargoStatus.SHIPPED);
    }
}
