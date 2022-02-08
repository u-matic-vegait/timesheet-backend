package rs.vegait.sigma.timesheet.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ClientDto;
import rs.vegait.sigma.timesheet.model.Client;

@Component
public class ClientToClientDto implements Converter<Client, ClientDto> {

	@Override
	public ClientDto convert(Client source) {
		ClientDto target = new ClientDto();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setAddress(source.getAddress());
		target.setCity(source.getCity());
		target.setZipcode(source.getZipcode());
		target.setCountry(source.getCountry());
		target.setIsDeleted(source.getisdeleted());

		return target;
	}

}
