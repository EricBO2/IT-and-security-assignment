package se.me.demo.model;

import jakarta.validation.constraints.NotBlank;
import se.me.demo.util.ValidPassword;

/**
 * Är en DTO klass av AppUserklassen.
 * Detta gör vi för att inte koppla användaren direkt till databasen.
 * Innehåller även felmeddelanden ifall användarnamn och lösenord inte godkänts.
 */
public class AppUserDTO {

    @NotBlank(message = "Please enter username")
    private String username;

    @ValidPassword
    private String password;

    @NotBlank(message = "Invalid role")
    private String role;

    private Boolean consentGiven;


    public String getUsername() {
        return username;
    }

    public AppUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AppUserDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public Boolean getConsentGiven() {
        return consentGiven;
    }

    public AppUserDTO setConsentGiven(Boolean consentGiven) {
        this.consentGiven = consentGiven;
        return this;
    }
}
