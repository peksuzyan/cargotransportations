package com.tsystems.cargotransportations.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Basic class that represents a user entity.
 */
@Entity
public class User {
    /**
     * Identifier of a user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of a user.
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Password of a user.
     */
    @Column(name = "password")
    private String password;

    /**
     * Role of a user.
     */
    @Column(name = "user_name")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    /**
     * Date of a user registration.
     */
    @Column(name = "creation_date")
    private Date creationDate;

    /**
     * Gets id.
     *
     * @return id id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets password.
     *
     * @return password password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets userRole.
     *
     * @return userRole userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Gets creationDate.
     *
     * @return creationDate creationDate
     */
    public Date getCreationDate() {
        return creationDate;
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
     * Sets name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets password.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets userRole.
     *
     * @param userRole userRole
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Sets creationDate.
     *
     * @param creationDate creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
