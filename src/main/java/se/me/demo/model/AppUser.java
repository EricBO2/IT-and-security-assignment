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

    @Size(min = 8, message = "must be minimum 8 characters")
    @Size(max = 20, message = "must be max 20 characters")
    private String password;

    @NotBlank(message = "role may not be empty")
    private String role;

    private Boolean consentGiven;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(Boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}
