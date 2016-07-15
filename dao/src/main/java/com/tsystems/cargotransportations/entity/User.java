package com.tsystems.cargotransportations.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

import static com.tsystems.cargotransportations.constants.FieldsMapping.*;
import static com.tsystems.cargotransportations.constants.ValidationCodes.USER_EMAIL_IS_EMPTY;
import static com.tsystems.cargotransportations.constants.ValidationCodes.USER_EMAIL_IS_NOT_EMAIL;
import static com.tsystems.cargotransportations.constants.ValidationCodes.USER_PASSWORD_IS_EMPTY;

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
    @Column(name = ID)
    private int id;

    /**
     * Email of a user.
     */
    @NotEmpty(message = USER_EMAIL_IS_EMPTY)
    @Email(message = USER_EMAIL_IS_NOT_EMAIL)
    @Column(name = EMAIL, unique = true)
    private String email;

    /**
     * Password of a user.
     */
    @NotEmpty(message = USER_PASSWORD_IS_EMPTY)
    @Column(name = PASSWORD)
    private String password;

    /**
     * Role of a user.
     */
    @Column(name = USER_ROLE)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    /**
     * Date of a user registration.
     */
    @Column(name = CREATION_DATE)
    private Date creationDate;

    /**
     * Name of a user.
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * something else
     */
    @Column(name = "driver_number")
    private int driverNumber;

    /**
     * Default constructor.
     */
    public User() {}

    /**
     * Setup initial values for creating user entity.
     */
    @PrePersist
    public void setupDefaultValues() {
        if (userRole == null) userRole = UserRole.USER;
        creationDate = new Date();
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
     * Gets password.
     *
     * @return password password
     */
    public String getPassword() {
        return password;
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
     * Gets userRole.
     *
     * @return userRole userRole
     */
    public UserRole getUserRole() {
        return userRole;
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
     * Gets creationDate.
     *
     * @return creationDate creationDate
     */
    public Date getCreationDate() {
        return creationDate;
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
