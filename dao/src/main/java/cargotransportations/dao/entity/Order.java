package cargotransportations.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    private int id;
    private boolean active;
    private List<OrderEntry> orderEntries;
    private Truck truck;
    private List<Driver> drivers;

    public Order() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    @OneToOne
    @JoinColumn(name = "truck_id")
    public Truck getTruck() {
        return truck;
    }

    @OneToMany(mappedBy = "order")
    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
