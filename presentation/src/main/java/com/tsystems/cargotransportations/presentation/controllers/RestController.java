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

@Controller("webclient")
@RequestMapping(value = "/rest")
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
            value = "/message",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public String getMessage() {
        return "Hello, World!";
    }

    @RequestMapping(
            value = "/driver/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public Driver getDriver(@PathVariable("id") int id) {
        return driverService.read(id);
    }

/*    @RequestMapping(
            value = "/driver",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public DriverStatus getDriverStatus(@RequestBody String email) {
        Driver driver = driverService.getByEmail(email);
        return driver.getStatus();
    }*/

    @RequestMapping(
            value = "/driver",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public String getDriverStatus(
            @RequestHeader(name = BindingProvider.USERNAME_PROPERTY) String email,
            @RequestHeader(name = BindingProvider.PASSWORD_PROPERTY) String password) {
        if (!userService.authenticate(email, password)) return null;
        Driver driver = driverService.getByEmail(email);
        return driver.getStatus().toString();
    }

    @RequestMapping(
            value = "/driver/{email}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public OrderDetails getOrderDetails(
            @PathVariable("email") String email) {
        OrderDetails orderDetails = new OrderDetails();
        Driver driver = driverService.getByEmail(email);
        if (driver == null) return orderDetails;
        Truck truck = driver.getTruck();
        if (truck == null) return orderDetails;
        orderDetails.setTruckNumber(truck.getNumber());
        Order order = orderService.getByStatusAndTruck(OrderStatus.OPEN, truck);
        if (order == null) return orderDetails;
        orderDetails.setDrivers(OrderDetailsUtil.buildDriversMap(order.getDrivers()));
        orderDetails.setCargoes(OrderDetailsUtil.buildCargoesMap(order.getCargoes()));
        orderDetails.setRoutePoints(order.getRoute().getCities());
        return orderDetails;
    }

    @RequestMapping(
            value = "/driver",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public boolean changeStatusByEmail(
            @RequestBody String email,
            @RequestBody String status) {
        Driver driver = driverService.getByEmail(email);
        if (driver == null) return false;
        driver.setStatus(DriverStatus.valueOf(status));
        driverService.update(driver);
        return true;
    }

    @RequestMapping(
            value = "/cargo",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public boolean completeCargoById(
            @RequestBody int id) {
        Cargo cargo = cargoService.read(id);
        if (cargo == null) return false;
        cargo.setStatus(CargoStatus.DELIVERED);
        cargoService.update(cargo);
        return true;
    }

/*    @GET
    @Path("/driver/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Driver getDriver(@PathParam("id") String id) {
        return driverService.read(Integer.parseInt(id));
    }*/

/*    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean authenticate(User user) {
        return userService.authenticate(user.getEmail(), user.getPassword());
    }*/

/*    @POST
    @Path("/driver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changeStatus(String status) {
        Driver driver = driverService.getByEmail("admin@admin.ru");
        if (driver == null) return false;
        driver.setStatus(DriverStatus.valueOf(status));
        return true;
    }*/
}

