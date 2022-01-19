package rs.vegait.sigma.timesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.vegait.sigma.timesheet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findFirstByUsername(String username);

	Optional<User> findFirstByUsernameAndPassword(String username, String password);
}
