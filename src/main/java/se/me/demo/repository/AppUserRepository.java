package se.me.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.me.demo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
