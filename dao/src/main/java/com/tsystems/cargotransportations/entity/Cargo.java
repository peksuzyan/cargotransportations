package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Basic class that represents a cargo entity.
 */
@Entity
public class Cargo implements Serializable {

    /**
     * Identifier of a cargo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Unique personal number of a cargo.
     */
    @Column(name = "number")
    private int number;

    /**
     * Ordinary name of a cargo for representing to user.
     */
    @Column(name = "name")
    private String name;

    /**
     * Weight of a cargo.
     */
    @Column(name = "weight")
    private double weight;

    /**
     * City is where cargo has to departure.
     */
    @Column(name = "departure_city")
    private String departureCity;

    /**
     * City is where cargo has to arrive.
     */
    @Column(name = "arrival_city")
    private String arrivalCity;

    /**
     * Represents current delivery status of a cargo.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    /**
     * Single default-constructor.
     */
    public Cargo() {}

    /**
     * Classic equals method.
     * @param o passed object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cargo cargo = (Cargo) o;

        if (id != cargo.id) return false;
        if (Double.compare(cargo.weight, weight) != 0) return false;
        if (!name.equals(cargo.name)) return false;
        if (!departureCity.equals(cargo.departureCity)) return false;
        if (!arrivalCity.equals(cargo.arrivalCity)) return false;
        return status == cargo.status;

    }

    /**
     * Classic hashCode method.
     * @return hashCode of given object
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + departureCity.hashCode();
        result = 31 * result + arrivalCity.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    /**
     * Gets id.
     *
     * @return id id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets number.
     *
     * @return number number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets name.
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets weight.
     *
     * @return weight weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets departureCity.
     *
     * @return departureCity departureCity
     */
    public String getDepartureCity() {
        return departureCity;
    }

    /**
     * Sets departureCity.
     *
     * @param departureCity departureCity
     */
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    /**
     * Gets arrivalCity.
     *
     * @return arrivalCity arrivalCity
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * Sets arrivalCity.
     *
     * @param arrivalCity arrivalCity
     */
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    /**
     * Gets status.
     *
     * @return status status
     */
    public CargoStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(CargoStatus status) {
        this.status = status;
    }
}
