package cargotransportations.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Simulator {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CargoTransportations");
    }
}
