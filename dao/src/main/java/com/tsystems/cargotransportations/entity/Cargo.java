package com.tsystems.cargotransportations.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.Objects;

import static com.tsystems.cargotransportations.constants.FieldsMapping.*;
import static com.tsystems.cargotransportations.constants.ValidationCodes.*;
import static com.tsystems.cargotransportations.constants.ValidationValues.CARGO_MAX_WEIGHT;
import static com.tsystems.cargotransportations.constants.ValidationValues.CARGO_MIN_WEIGHT;

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
    @Column(name = ID)
    private int id;

    /**
     * Ordinary name of a cargo for representing to user.
     */
    @NotEmpty(message = CARGO_NAME_IS_EMPTY)
    @Column(name = NAME)
    private String name;

    /**
     * Weight of a cargo.
     */
    @DecimalMax(value = CARGO_MAX_WEIGHT, message = CARGO_WEIGHT_IS_MORE_MAX)
    @DecimalMin(value = CARGO_MIN_WEIGHT, message = CARGO_WEIGHT_IS_LESS_MIN)
    @Column(name = WEIGHT)
    private double weight;

    /**
     * City is where cargo has to departure.
     */
    @NotEmpty(message = CARGO_DEPARTURE_CITY_IS_EMPTY)
    @Column(name = DEPARTURE_CITY)
    private String departureCity;

    /**
     * City is where cargo has to arrive.
     */
    @NotEmpty(message = CARGO_ARRIVAL_CITY_IS_EMPTY)
    @Column(name = ARRIVAL_CITY)
    private String arrivalCity;

    /**
     * Represents current delivery status of a cargo.
     */
    @Column(name = STATUS)
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    /**
     * Unique personal number of a cargo.
     */
    @Column(name = "number")
    private int number;


    /**
     * Default constructor.
     */
    public Cargo() {}

    /**
     * Setup initial values for creating cargo entity.
     */
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
