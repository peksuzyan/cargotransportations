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

    @ElementCollection
    private List<String> cities;

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
}
