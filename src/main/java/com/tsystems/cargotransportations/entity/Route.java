package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Route implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", unique = true)
    private int number;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> cities;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "duration")
    private int duration;

    public Route() {}

    /**
     * Gets id.
     *
     * @return id id
     */
    public int getId() {
        return id;
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
     * Gets cities.
     *
     * @return cities cities
     */
    public List<String> getCities() {
        return cities;
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
     * Gets arrivalCity.
     *
     * @return arrivalCity arrivalCity
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * Gets duration.
     *
     * @return duration duration
     */
    public int getDuration() {
        return duration;
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
     * Sets number.
     *
     * @param number number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets cities.
     *
     * @param cities cities
     */
    public void setCities(List<String> cities) {
        this.cities = cities;
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
     * Sets arrivalCity.
     *
     * @param arrivalCity arrivalCity
     */
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    /**
     * Sets duration.
     *
     * @param duration duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
