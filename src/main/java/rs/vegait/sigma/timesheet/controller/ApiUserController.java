package rs.vegait.sigma.timesheet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.vegait.sigma.timesheet.dto.UserDto;
import rs.vegait.sigma.timesheet.model.User;
import rs.vegait.sigma.timesheet.service.UserService;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Converter<User, UserDto> toDto;

	@Autowired
	private Converter<List<User>, List<UserDto>> toDtoList;

	@Autowired
	private Converter<UserDto, User> toUser;

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> get(@PathVariable Long id) {
		Optional<User> user = userService.one(id);

		if (user.isPresent()) {
			UserDto body = toDto.convert(user.get());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> get(@RequestParam(defaultValue = "0") int page) {

		Page<User> usersPage = userService.all(page);
		List<User> users = usersPage.getContent();
		List<UserDto> body = toDtoList.convert(users);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

}
