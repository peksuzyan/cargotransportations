package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DatabaseMapper.*;

/**
 * Basic class that represents an order entity.
 */
@Entity
@Table(name = ORDERS_TABLE)
public class Order implements Serializable {

    /**
     * Identifier of an order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;

    /**
     * Represents that can whether be an order is used yet.
     */
    @Column(name = STATUS)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    /**
     * Represents which of cargoes include to the order.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = ORDER_ID)
    private List<Cargo> cargoes;

    /**
     * Represents a truck that performs this order.
     */
    @OneToOne
    @JoinColumn(name = TRUCK_ID)
    private Truck truck;

    /**
     * Represents a list of drivers that perform this order.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = ORDER_ID)
    private List<Driver> drivers;

    /**
     * Represents a route that is followed by truck.
     */
    @OneToOne
    @JoinColumn(name = ROUTE_ID)
    private Route route;

    /**
     * Date when this order was created.
     */
    @Column(name = CREATION_DATE)
    private Date creationDate;

    /**
     * Default constructor.
     */
    public Order() {}

    /**
     * Setup initial values for creating user entity.
     */
    @PrePersist
    public void setupDefaultValues() {
        creationDate = new Date();
        status = OrderStatus.OPEN;
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
     * Gets status.
     *
     * @return status status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Gets cargoes.
     *
     * @return cargoes cargoes
     */
    public List<Cargo> getCargoes() {
        return cargoes;
    }

    /**
     * Sets cargoes.
     *
     * @param cargoes cargoes
     */
    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    /**
     * Gets truck.
     *
     * @return truck truck
     */
    public Truck getTruck() {
        return truck;
    }

    /**
     * Sets truck.
     *
     * @param truck truck
     */
    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    /**
     * Gets drivers.
     *
     * @return drivers drivers
     */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Sets drivers.
     *
     * @param drivers drivers
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    /**
     * Gets route.
     *
     * @return route route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Sets route.
     *
     * @param route route
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * Gets creationDate.
     *
     * @return creationDate creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creationDate.
     *
     * @param creationDate creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
