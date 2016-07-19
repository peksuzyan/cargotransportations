package com.tsystems.cargotransportations.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class RestClient {
    public static void main(String[] args) throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(
                "http://localhost:8080/RestTesting-1.0-SNAPSHOT/rest/car/post");
        Car car = new Car("Audi");
        ObjectMapper objectMapper = new ObjectMapper();
        String JSONCar = objectMapper.writeValueAsString(car);
        ClientResponse clientResponse = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, JSONCar);
        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("Failed: HTTP error code : "
                    + clientResponse.getStatus());
        }

        String output = clientResponse.getEntity(String.class);
        Car newCar = objectMapper.readValue(output, Car.class);
        System.out.println("old car: " + car);
        System.out.println("output from server ...");
        System.out.println(newCar);
    }
}

