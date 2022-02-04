package rs.vegait.sigma.timesheet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ClientDto;
import rs.vegait.sigma.timesheet.model.Client;

@Component
public class ClientListToClientDtoList implements Converter<List<Client>, List<ClientDto>> {

	@Autowired
	private ClientToClientDto toDto;

	@Override
	public List<ClientDto> convert(List<Client> source) {
		List<ClientDto> target = new ArrayList<>();

		for (Client client : source) {
			ClientDto dto = toDto.convert(client);
			target.add(dto);
		}

		return target;
	}
}
