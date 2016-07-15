package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.tsystems.cargotransportations.constants.FieldsMapping.DURATION;
import static com.tsystems.cargotransportations.constants.FieldsMapping.ID;

@Entity
public class Route implements Serializable {

    /**
     * Represents identifier of a route.
     */
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Sequence route points expressed by cities.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> cities;

    /**
     * Route duration in time.
     */
    @Column(name = DURATION)
    private int duration;

    /**
     * Default constructor.
     */
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
     * Gets cities.
     *
     * @return cities cities
     */
    public List<String> getCities() {
        return cities;
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
     * Sets cities.
     *
     * @param cities cities
     */
    public void setCities(List<String> cities) {
        this.cities = cities;
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
