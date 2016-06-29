package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Basic class that represents a driver entity.
 */
@Entity
public class Driver implements Serializable {

    /**
     * Identifier of a driver.
     */
    private int id;

    /**
     * Unique personal number of a driver.
     */
    private int number;

    /**
     * Ordinary first name of a driver.
     */
    private String firstName;

    /**
     * Ordinary last name of a driver.
     */
    private String lastName;

    /**
     * Represents how many time driver has worked already.
     */
    private int hours;

    /**
     * Represents current status of a driver.
     */
    private DriverStatus status;

    /**
     * City where is a driver now.
     */
    private String city;

    /**
     * Current truck assigned to a driver.
     */
    private Truck truck;

    public Driver() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "number", unique = true)
    public int getNumber() {
        return number;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "hours")
    public int getHours() {
        return hours;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public DriverStatus getStatus() {
        return status;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @ManyToOne
    @JoinColumn(name = "truck_id")
    public Truck getTruck() {
        return truck;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
