package com.tsystems.cargotransportations.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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
    @NotEmpty(message = "{validation_cargo_name_NotEmpty}")
    @Column(name = "name")
    private String name;

    /**
     * Weight of a cargo.
     */
    @DecimalMax(value = "100", message = "{validation_cargo_weight_DecimalMax}")
    @DecimalMin(value = "0", message = "{validation_cargo_weight_DecimalMin}")
    @Column(name = "weight")
    private double weight;

    /**
     * City is where cargo has to departure.
     */
    @NotEmpty(message = "{validation_cargo_departure_NotEmpty}")
    @Column(name = "departure_city")
    private String departureCity;

    /**
     * City is where cargo has to arrive.
     */
    @NotEmpty(message = "{validation_cargo_arrival_NotEmpty}")
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

    @PrePersist
    public void setupDefaultValues() {
        status = CargoStatus.PREPARED;
    }

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
        return id == cargo.id &&
                Double.compare(cargo.weight, weight) == 0 &&
                Objects.equals(name, cargo.name) &&
                Objects.equals(departureCity, cargo.departureCity) &&
                Objects.equals(arrivalCity, cargo.arrivalCity) &&
                status == cargo.status;
    }

    /**
     * Classic hashCode method.
     * @return hashCode of given object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, departureCity, arrivalCity, status);
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
