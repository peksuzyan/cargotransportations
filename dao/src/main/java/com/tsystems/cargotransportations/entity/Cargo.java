package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Basic class that represents a cargo entity.
 */
@Entity
@Table(uniqueConstraints =
    @UniqueConstraint(columnNames = {"departure_city", "arrival_city"}))
public class Cargo implements Serializable {

    /**
     * Identifier of a cargo.
     */
    private int id;

    /**
     * Unique personal number of a cargo.
     */
    private int number;

    /**
     * Ordinary name of a cargo for representing to user.
     */
    private String name;

    /**
     * Weight of a cargo.
     */
    private double weight;

    /**
     * City where is cargo has to departure.
     */
    private String departureCity;

    /**
     * City where is cargo has to arrival.
     */
    private String arrivalCity;

    /**
     * Represents current delivery status of a cargo.
     */
    private CargoStatus status;

    public Cargo() {}

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "weight")
    public double getWeight() {
        return weight;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public CargoStatus getStatus() {
        return status;
    }

    @Column(name = "departure_city")
    public String getDepartureCity() {
        return departureCity;
    }

    /**
     * Gets arrivalCity.
     *
     * @return arrivalCity arrivalCity
     */
    @Column(name = "arrival_city")
    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    /**
     * Sets departureCity.
     *
     * @param departureCity departureCity
     */
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
}
