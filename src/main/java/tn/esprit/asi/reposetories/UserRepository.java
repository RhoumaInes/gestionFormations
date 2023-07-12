package tn.esprit.asi.reposetories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.asi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLogin(String login);
}
