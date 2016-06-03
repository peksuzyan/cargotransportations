package cargotransportations.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "Route", uniqueConstraints =
        @UniqueConstraint(columnNames = {"source", "destination"}))
public class Route {

    private int id;
    private String source;
    private String destination;
    private int distance;

    public Route() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "source")
    public String getSource() {
        return source;
    }

    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    @Column(name = "distance")
    public int getDistance() {
        return distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
