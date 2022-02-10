package rs.vegait.sigma.timesheet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.vegait.sigma.timesheet.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<Project> findByName(String name);

	List<Project> findByIsdeletedFalse();

}
