package com.tsystems.cargotransportations.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import static com.tsystems.cargotransportations.constants.mapping.DatabaseMapper.*;
import static com.tsystems.cargotransportations.constants.codes.ValidationCodes.*;
import static com.tsystems.cargotransportations.constants.values.ValidationValues.*;

/**
 * Basic class that represents a truck entity.
 */
@Entity
public class Truck implements Serializable {

    /**
     * Identifier of a truck.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;

    /**
     * Unique personal number of a truck.
     */
    @Pattern(regexp = TRUCK_NUMBER_PATTERN, message = TRUCK_NUMBER_NOT_MATCH_PATTERN)
    @Column(name = NUMBER, unique = true)
    private String number;

    /**
     * Represents how many people can to work together in a given truck.
     */
    @Max(value = TRUCK_MAX_PEOPLE, message = TRUCK_PEOPLE_IS_MORE_MAX)
    @Min(value = TRUCK_MIN_PEOPLE, message = TRUCK_PEOPLE_IS_LESS_MIN)
    @Column(name = PEOPLE)
    private int people;

    /**
     * Represents may be a truck is used now.
     */
    @Column(name = ACTIVE)
    private boolean active;

    /**
     * Total capacity of a truck.
     */
    @DecimalMax(value = TRUCK_MAX_CAPACITY, message = TRUCK_CAPACITY_IS_MORE_MAX)
    @DecimalMin(value = TRUCK_MIN_CAPACITY, message = TRUCK_CAPACITY_IS_LESS_MIN)
    @Column(name = CAPACITY)
    private double capacity;

    /**
     * City where is a truck now.
     */
    @NotEmpty(message = TRUCK_CITY_IS_EMPTY)
    @Column(name = CITY)
    private String city;

    /**
     * Default constructor.
     */
    public Truck() {}

    /**
     * Setup initial values for creating user entity.
     */
    @PrePersist
    public void setupDefaultValues() {
        active = true;
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
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets people.
     *
     * @return people people
     */
    public int getPeople() {
        return people;
    }

    /**
     * Sets people.
     *
     * @param people people
     */
    public void setPeople(int people) {
        this.people = people;
    }

    /**
     * Gets active.
     *
     * @return active active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets capacity.
     *
     * @return capacity capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity capacity
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets city.
     *
     * @return city city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
