package cargotransportations.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderEntry", uniqueConstraints =
        @UniqueConstraint(columnNames = {"departure_city", "arrival_city"}))
public class OrderEntry implements Serializable {

    private int id;
    private String departureCity;
    private String arrivalCity;
    private Cargo cargo;
    private Order order;

    public OrderEntry() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "departure_city")
    public String getDepartureCity() {
        return departureCity;
    }

    @Column(name = "arrival_city")
    public String getArrivalCity() {
        return arrivalCity;
    }

    @OneToOne(mappedBy = "orderEntry", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    public void setDepartureCity(String name) {
        this.departureCity = name;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
