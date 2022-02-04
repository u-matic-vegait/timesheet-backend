package rs.vegait.sigma.timesheet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.vegait.sigma.timesheet.dto.ClientDto;
import rs.vegait.sigma.timesheet.model.Client;
import rs.vegait.sigma.timesheet.service.ClientService;

@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private Converter<Client, ClientDto> toDto;

	@Autowired
	private Converter<List<Client>, List<ClientDto>> toDtoList;

	@Autowired
	private Converter<ClientDto, Client> toClient;

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> get(@PathVariable Long id) {
		Optional<Client> client = clientService.one(id);

		if (client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		if (client.isPresent()) {
			ClientDto body = toDto.convert(client.get());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<ClientDto>> getAll() {

		List<Client> clients = clientService.all();
		List<ClientDto> body = toDtoList.convert(clients);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClientDto> add(@RequestBody @Validated ClientDto reqBody) {

		if (reqBody.getId() != null) {
			System.out.println(reqBody);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Client toAdd = toClient.convert(reqBody);

		Client persisted = clientService.save(toAdd);

		ClientDto respBody = toDto.convert(persisted);
		return new ResponseEntity<>(respBody, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Optional<Client> client = clientService.one(id);

		if (client != null) {
			clientService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<ClientDto> edit(@RequestBody ClientDto clientDto) {

		Optional<Client> client = clientService.one(clientDto.getId());

		if (client == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Client toSave = toClient.convert(clientDto);
		Client persisted = clientService.save(toSave);

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
