package com.tsystems.cargotransportations.test;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.service.implementation.RouteServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteServiceImplTest {

    @Test
    public void getSuitableRoutesByOrder() {
        RouteServiceImpl service = new RouteServiceImpl();
        Order order = new Order();
        List<Cargo> cargoes = new ArrayList<>();
        Cargo cargo;
        cargo = new Cargo();
        cargo.setDepartureCity("B");
        cargo.setArrivalCity("C");
        cargoes.add(cargo);
        cargo = new Cargo();
        cargo.setDepartureCity("A");
        cargo.setArrivalCity("B");
        cargoes.add(cargo);
        cargo = new Cargo();
        cargo.setDepartureCity("A");
        cargo.setArrivalCity("B");
        cargoes.add(cargo);
        cargo = new Cargo();
        cargo.setDepartureCity("C");
        cargo.setArrivalCity("D");
        cargoes.add(cargo);
        order.setCargoes(cargoes);
        Assert.assertTrue(service.getSuitableRoutesByOrder(order).size() == 1);

        cargo.setDepartureCity("A");
        Assert.assertTrue(service.getSuitableRoutesByOrder(order).size() == 0);

        cargo.setDepartureCity("C");
        cargo = new Cargo();
        cargo.setDepartureCity("B");
        cargo.setArrivalCity("C");
        order.getCargoes().add(cargo);
        Assert.assertTrue(service.getSuitableRoutesByOrder(order).size() == 1);
    }
}
