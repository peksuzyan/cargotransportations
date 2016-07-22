package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.presentation.grids.OrderDetails;
import com.tsystems.cargotransportations.presentation.grids.OrderDetailsUtil;
import com.tsystems.cargotransportations.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import javax.xml.ws.BindingProvider;

@RequestMapping("/rest")
@Controller
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CargoService cargoService;

    @RequestMapping(
            value = "/driver",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public OrderDetails getOrderDetails(
            @RequestHeader(value = BindingProvider.USERNAME_PROPERTY) String email,
            @RequestHeader(value = BindingProvider.PASSWORD_PROPERTY) String password) {
        OrderDetails orderDetails = new OrderDetails();
        if (!userService.authenticate(email, password)) return orderDetails;
        Driver driver = driverService.getByEmail(email);
        if (driver == null) return orderDetails;
        Truck truck = driver.getTruck();
        if (truck == null) return orderDetails;
        orderDetails.setTruckNumber(truck.getNumber());
        Order order = orderService.getByStatusAndTruck(OrderStatus.PERFORMING, truck);
        if (order == null) return orderDetails;
        orderDetails.setOrderId(order.getId());
        orderDetails.setDrivers(OrderDetailsUtil.buildDriversMap(order.getDrivers()));
        orderDetails.setCargoes(OrderDetailsUtil.buildCargoesMap(order.getCargoes()));
        orderDetails.setRoutePoints(order.getRoute().getCities());
        return orderDetails;
    }

    @RequestMapping(
            value = "/driver",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public boolean changeStatusByEmail(
            @RequestHeader(value = BindingProvider.USERNAME_PROPERTY) String email,
            @RequestHeader(value = BindingProvider.PASSWORD_PROPERTY) String password,
            @RequestBody String status) {
        return userService.authenticate(email, password) &&
                driverService.changeStatusByEmail(email, DriverStatus.valueOf(status));
    }

    @RequestMapping(
            value = "/cargo",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public boolean completeCargoById(
            @RequestHeader(value = BindingProvider.USERNAME_PROPERTY) String email,
            @RequestHeader(value = BindingProvider.PASSWORD_PROPERTY) String password,
            @RequestBody int id) {
        return userService.authenticate(email, password)
                && cargoService.changeStatusById(id);
    }
}

