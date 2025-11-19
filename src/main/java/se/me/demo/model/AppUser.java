package se.me.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @Entity gör att Spring vet att det är en databasentitet.
 */
@Entity
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  Fält för användarnamn, lösenord och roll
    private String username;
    private String password;
    private String role;

    //  Om användaren godkänt t.ex. villkor
    private Boolean consentGiven;

    // ======== GETTERS & SETTERS ==========

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
