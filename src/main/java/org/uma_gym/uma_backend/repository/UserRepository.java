package org.uma_gym.uma_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uma_gym.uma_backend.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // automatski radi kada se poklapa findBy[naziv](type naziv)
    //koristice se za pronalazak User-a sa istim email-om
    Optional<User> findByEmail(String email);
}