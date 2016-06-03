package cargotransportations.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "Truck")
public class Truck {

    private int id;
    private String number;
    private int people;
    private boolean active;
    private double capacity;
    private String city;

    public Truck() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    @Column(name = "people")
    public int getPeople() {
        return people;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    @Column(name = "capacity")
    public double getCapacity() {
        return capacity;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
