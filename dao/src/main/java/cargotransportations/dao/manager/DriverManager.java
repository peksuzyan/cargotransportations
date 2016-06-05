package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Driver;
import cargotransportations.dao.util.DriverStatus;

import javax.persistence.EntityManager;

public class DriverManager extends AbstractManager<Driver> {

    public DriverManager(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Driver> getItemClass() {
        return Driver.class;
    }

    public Driver create(String firstName, String lastName, String city) {
        Driver driver = super.create();
        getEntityManager().getTransaction().begin();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setCity(city);
        driver.setHours(0);
        driver.setStatus(DriverStatus.FREE);
        getEntityManager().getTransaction().commit();
        return driver;
    }
}
