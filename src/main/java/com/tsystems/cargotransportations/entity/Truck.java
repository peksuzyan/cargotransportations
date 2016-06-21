package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Basic class that represents a truck entity.
 */
@Entity
public class Truck implements Serializable {

    /**
     * Identifier of a truck.
     */
    private int id;

    /**
     * Unique personal number of a truck.
     */
    private String number;

    /**
     * Represents how many people can to work together in a given truck.
     */
    private int people;

    /**
     * Represents may be a truck is used now.
     */
    private boolean active;

    /**
     * Total capacity of a truck.
     */
    private double capacity;

    /**
     * City where is a truck now.
     */
    private String city;

    public Truck() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "number", unique = true)
    public String getNumber() {
        return number;
    }

    @Column(name = "people")
    public int getPeople() {
        return people;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    @Column(name = "capacity")
    public double getCapacity() {
        return capacity;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
