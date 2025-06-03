package se.me.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.Length;
import se.me.demo.util.ValidPassword;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter username")
    private String username;

    @ValidPassword
    private String password;

    @NotBlank(message = "Invalid role")
    private String role;

    private Boolean consentGiven;


}
