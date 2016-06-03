package cargotransportations.dao.entity;

import cargotransportations.dao.util.CargoType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Entry")
public class Entry implements Serializable {

    private int id;
    private String name;
    private CargoType type;
    private Cargo cargo;
    private Order order;

    public Entry() {}

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

    @Column(name = "type")
    public CargoType getType() {
        return type;
    }

    @OneToOne(mappedBy = "entry")
    public Cargo getCargo() {
        return cargo;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(CargoType type) {
        this.type = type;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
