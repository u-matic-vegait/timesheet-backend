package rs.vegait.sigma.timesheet.service;

import java.util.List;
import java.util.Optional;

import rs.vegait.sigma.timesheet.model.Project;

public interface ProjectService {

	Optional<Project> one(Long id);

	List<Project> all();

	Project save(Project project);

	void delete(Long id);

	Optional<Project> findByName(String name);

}
