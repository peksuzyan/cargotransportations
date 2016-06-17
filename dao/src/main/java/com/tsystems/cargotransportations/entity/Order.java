package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Basic class that represents an order entity.
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    /**
     * Identifier of an order.
     */
    private int id;

    /**
     * Unique personal number of an order.
     */
    private int number;

    /**
     * Represents that can whether be an order is used yet.
     */
    private boolean active;

    /**
     * Represents which of cargoes include to the order.
     */
    private List<Cargo> cargoes;

    /**
     * Represents a truck that performs this order.
     */
    private Truck truck;

    /**
     * Represents a list of drivers that perform this order.
     */
    private List<Driver> drivers;

    /**
     * Date when this order was created.
     */
    private Date creationDate;

    public Order() {}

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

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    @OneToMany
    @JoinColumn(name = "order_id")
    public List<Cargo> getCargoes() {
        return cargoes;
    }

    @OneToOne
    @JoinColumn(name = "truck_id")
    public Truck getTruck() {
        return truck;
    }

    @OneToMany
    @JoinColumn(name = "order_id")
    public List<Driver> getDrivers() {
        return drivers;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
