package cargotransportations.entity;

import cargotransportations.util.CargoStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cargo", uniqueConstraints =
    @UniqueConstraint(columnNames = {"departure_city", "arrival_city"}))
public class Cargo implements Serializable {

    private int id;
    private String name;
    private double weight;
    private String departureCity;
    private String arrivalCity;
    private CargoStatus status;

    public Cargo() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "weight")
    public double getWeight() {
        return weight;
    }

    @Column(name = "status")
    public CargoStatus getStatus() {
        return status;
    }

    @Column(name = "departure_city")
    public String getDepartureCity() {
        return departureCity;
    }

    @Column(name = "arrival_city")
    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
