package com.tsystems.cargotransportations.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/car")
public class GetCarService {

    @GET
    @Path("/{brandName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("brandName") String brandName) {
        return new Car(brandName);
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Car patchCar(Car car) {
        return new Car(car.getModel());
    }
}
