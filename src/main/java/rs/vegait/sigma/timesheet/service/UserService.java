package rs.vegait.sigma.timesheet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import rs.vegait.sigma.timesheet.model.User;

public interface UserService {

	Optional<User> one(Long id);

	List<User> all();

	Page<User> all(int pageNo);

	User save(User user);

	void delete(Long id);

	Optional<User> findbyUsername(String username);

}
