package cargotransportations.dao.entity;

import cargotransportations.dao.util.CargoStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cargo")
public class Cargo implements Serializable {

    private int id;
    private String name;
    private double weight;
    private CargoStatus status;
    private Entry entry;

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

    @OneToOne
    @JoinColumn(name = "entry_id")
    public Entry getEntry() {
        return entry;
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

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
