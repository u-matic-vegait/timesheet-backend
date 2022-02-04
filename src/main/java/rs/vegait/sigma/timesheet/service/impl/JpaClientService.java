package rs.vegait.sigma.timesheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.vegait.sigma.timesheet.model.Client;
import rs.vegait.sigma.timesheet.repository.ClientRepository;
import rs.vegait.sigma.timesheet.service.ClientService;

@Service
public class JpaClientService implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Optional<Client> one(Long id) {

		Optional<Client> client = clientRepository.findById(id);
		if (client.get().getisdeleted() == false) {
			return client;
		} else {
			return null;
		}

		// return clientRepository.findById(id);
	}

	@Override
	public List<Client> all() {

		List<Client> list = new ArrayList<>();
		var allClients = clientRepository.findAll();

		for (Client client : allClients) {
			if (client.getisdeleted() == false) {
				list.add(client);
			}
		}

		return list;

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
