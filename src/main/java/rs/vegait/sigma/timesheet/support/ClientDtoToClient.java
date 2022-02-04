package rs.vegait.sigma.timesheet.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ClientDto;
import rs.vegait.sigma.timesheet.model.Client;
import rs.vegait.sigma.timesheet.service.ClientService;

@Component
public class ClientDtoToClient implements Converter<ClientDto, Client> {

	@Autowired
	private ClientService clientService;

	@Override
	public Client convert(ClientDto source) {
		Client target = null;
		if (source.getId() != null) {
			target = clientService.one(source.getId()).get();
		}

		if (target == null) {
			target = new Client();
		}

		target.setName(source.getName());
		target.setAddress(source.getAddress());
		target.setCity(source.getCity());
		target.setZipcode(source.getZipcode());
		target.setCountry(source.getCountry());

		return target;
	}

}
