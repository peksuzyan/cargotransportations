package com.tsystems.cargotransportations.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static com.tsystems.cargotransportations.constants.DatabaseMapper.*;
import static com.tsystems.cargotransportations.constants.ValidationCodes.*;

/**
 * Basic class that represents a driver entity.
 */
@Entity
public class Driver implements Serializable {

    /**
     * Identifier of a driver.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;

    /**
     * Unique identifier of driver in form of an email.
     */
    @NotEmpty(message = DRIVER_EMAIL_IS_EMPTY)
    @Email(message = DRIVER_EMAIL_IS_NOT_EMAIL)
    @Column(name = EMAIL, unique = true)
    private String email;

    /**
     * Ordinary first name of a driver.
     */
    @NotEmpty(message = DRIVER_FIRST_NAME_IS_EMPTY)
    @Column(name = FIRST_NAME)
    private String firstName;

    /**
     * Ordinary last name of a driver.
     */
    @NotEmpty(message = DRIVER_LAST_NAME_IS_EMPTY)
    @Column(name = LAST_NAME)
    private String lastName;

    /**
     * Represents how many time driver has worked already.
     */
    @Column(name = HOURS)
    private int hours;

    /**
     * Represents current status of a driver.
     */
    @Column(name = STATUS)
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    /**
     * City where is a driver now.
     */
    @NotEmpty(message = DRIVER_CITY_IS_EMPTY)
    @Column(name = CITY)
    private String city;

    /**
     * Current truck assigned to a driver.
     */
    @ManyToOne
    @JoinColumn(name = TRUCK_ID)
    private Truck truck;

    /**
     * The date of beginning shift.
    */
    @Column(name = SHIFT_START)
    private Date shiftStart;

    /**
     * Default constructor.
     */
    public Driver() {}

    /**
     * Setup initial values for creating driver entity.
     */
    @PrePersist
    public void setupDefaultValues() {
        status = DriverStatus.FREE;
        hours = 0;
    }

    /**
     * Returns first and last names concatenation for convenience representation.
     * @return full name
     */
    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Gets id.
     *
     * @return id id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets firstName.
     *
     * @return firstName firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName.
     *
     * @param firstName firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName.
     *
     * @return lastName lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName.
     *
     * @param lastName lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets hours.
     *
     * @return hours hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets hours.
     *
     * @param hours hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Gets status.
     *
     * @return status status
     */
    public DriverStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    /**
     * Gets city.
     *
     * @return city city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets truck.
     *
     * @return truck truck
     */
    public Truck getTruck() {
        return truck;
    }

    /**
     * Sets truck.
     *
     * @param truck truck
     */
    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    /**
     * Gets shiftStart.
     *
     * @return shiftStart shiftStart
     */
    public Date getShiftStart() {
        return shiftStart;
    }

    /**
     * Sets shiftStart.
     *
     * @param shiftStart shiftStart
     */
    public void setShiftStart(Date shiftStart) {
        this.shiftStart = shiftStart;
    }
}
