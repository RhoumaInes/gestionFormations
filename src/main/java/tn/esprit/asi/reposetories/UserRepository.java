package tn.esprit.asi.reposetories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.asi.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.login= :login")
	Optional<User> findByLogin(@Param("login") String login);
}
