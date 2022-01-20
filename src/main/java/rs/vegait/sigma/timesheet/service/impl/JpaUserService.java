package rs.vegait.sigma.timesheet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rs.vegait.sigma.timesheet.enumerations.UserRole;
import rs.vegait.sigma.timesheet.model.User;
import rs.vegait.sigma.timesheet.repository.UserRepository;
import rs.vegait.sigma.timesheet.service.UserService;

@Service
public class JpaUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> one(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> all() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> all(int pageNo) {
		return userRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public User save(User user) {
		user.setRole(UserRole.USER);
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findbyUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

}
