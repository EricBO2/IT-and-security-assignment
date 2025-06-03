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


}
