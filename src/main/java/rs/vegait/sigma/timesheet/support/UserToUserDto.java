package rs.vegait.sigma.timesheet.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.UserDto;
import rs.vegait.sigma.timesheet.model.User;

@Component
public class UserToUserDto implements Converter<User, UserDto> {
	@Override
	public UserDto convert(User source) {
		UserDto target = new UserDto();

		target.setId(source.getId());
		target.setUsername(source.getUsername());

		return target;
	}
}
