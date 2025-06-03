package se.me.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.Length;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name may not be blank")
    private String username;

    //@Size(min = 6, message = "must be minimum 8 characters")
    private String password;

    @NotBlank(message = "role may not be empty")
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
