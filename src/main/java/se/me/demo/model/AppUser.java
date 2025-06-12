package se.me.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Objektet innehåller all information om användaren som skickas fram och tillbaka till databasen.
 * Eget Id genereras automatiskt.
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    private Boolean consentGiven;

    public Long getId() {
        return id;
    }

    public AppUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AppUser setRole(String role) {
        this.role = role;
        return this;
    }

    public Boolean getConsentGiven() {
        return consentGiven;
    }

    public AppUser setConsentGiven(Boolean consentGiven) {
        this.consentGiven = consentGiven;
        return this;
    }
}
