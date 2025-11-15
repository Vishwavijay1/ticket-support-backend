package com.ticketapp.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Make sure it says <AppUser, Long>, NOT <User, Long>
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}