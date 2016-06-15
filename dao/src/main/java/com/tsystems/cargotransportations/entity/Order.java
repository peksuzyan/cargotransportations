package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    private int id;
    private int number;
    private boolean active;
    private List<Cargo> cargoes;
    private Truck truck;
    private List<Driver> drivers;
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
