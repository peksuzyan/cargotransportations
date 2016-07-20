package com.tsystems.cargotransportations.webservice;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.service.interfaces.DriverService;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service("managementService")
@Path("/integration")
public class ManagementService {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean authenticate(User user) {
        return userService.authenticate(user.getEmail(), user.getPassword());
    }

    @POST
    @Path("/driver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changeStatus(String status) {
        Driver driver = driverService.getByEmail("admin@admin.ru");
        if (driver == null) return false;
        driver.setStatus(DriverStatus.valueOf(status));
        return true;
    }
}

