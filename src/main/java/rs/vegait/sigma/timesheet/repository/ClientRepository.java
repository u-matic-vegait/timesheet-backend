package rs.vegait.sigma.timesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.vegait.sigma.timesheet.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByName(String name);

}
