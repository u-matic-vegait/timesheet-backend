package rs.vegait.sigma.timesheet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.vegait.sigma.timesheet.exception.ResourceNotFoundException;
import rs.vegait.sigma.timesheet.model.Client;
import rs.vegait.sigma.timesheet.repository.ClientRepository;
import rs.vegait.sigma.timesheet.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Optional<Client> findOne(Long id) {

		var client = clientRepository.findById(id);

		if (client.get().getisdeleted() == true) {
			throw new ResourceNotFoundException("Not found client with id = " + id);
		}

		return client;
	}

	@Override
	public List<Client> all() {

		return clientRepository.findByIsDeletedFalse();

	}

	@Override
	public Client save(Client client) {
		client.setIsDeleted(false);
		return clientRepository.save(client);
	}

	@Override
	public void delete(Long id) {
		Client deleted = clientRepository.getOne(id);
		deleted.setIsDeleted(true);
		clientRepository.save(deleted);
	}

	@Override
	public Optional<Client> findByName(String name) {
		return clientRepository.findByName(name);
	}

}
