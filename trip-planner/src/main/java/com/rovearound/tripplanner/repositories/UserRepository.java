package com.rovearound.tripplanner.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rovearound.tripplanner.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
