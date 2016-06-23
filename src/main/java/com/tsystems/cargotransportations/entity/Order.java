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
    private OrderStatus status;

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
     * Represents a route that is followed by truck.
     */
    private Route route;

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

    /**
     * Gets status.
     *
     * @return status status
     */
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public OrderStatus getStatus() {
        return status;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public List<Cargo> getCargoes() {
        return cargoes;
    }

    @OneToOne
    @JoinColumn(name = "truck_id")
    public Truck getTruck() {
        return truck;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public List<Driver> getDrivers() {
        return drivers;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Gets route.
     *
     * @return route route
     */
    @OneToOne
    @JoinColumn(name = "route_id")
    public Route getRoute() {
        return route;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
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

    /**
     * Sets route.
     *
     * @param route route
     */
    public void setRoute(Route route) {
        this.route = route;
    }
}
