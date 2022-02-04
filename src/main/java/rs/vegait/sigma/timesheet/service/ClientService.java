package rs.vegait.sigma.timesheet.service;

import java.util.List;
import java.util.Optional;

import rs.vegait.sigma.timesheet.model.Client;

public interface ClientService {

	Optional<Client> one(Long id);

	List<Client> all();

	Client save(Client toAdd);

	Client delete(Long id);

	Optional<Client> findByName(String name);

}
