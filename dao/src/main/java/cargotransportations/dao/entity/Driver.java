package cargotransportations.dao.entity;

import cargotransportations.dao.util.DriverStatus;

import javax.persistence.*;

@Entity
@Table(name = "Driver")
public class Driver {

    private int id;
    private String firstName;
    private String lastName;
    private int hours;
    private DriverStatus status;
    private String city;
    private Truck truck;
    private Order order;

    public Driver() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "hours")
    public int getHours() {
        return hours;
    }

    @Column(name = "status")
    public DriverStatus getStatus() {
        return status;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @ManyToOne
    @JoinColumn(name = "truck_id")
    public Truck getTruck() {
        return truck;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
