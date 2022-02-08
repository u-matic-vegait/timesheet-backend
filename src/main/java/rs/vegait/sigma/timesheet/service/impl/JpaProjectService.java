package rs.vegait.sigma.timesheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.vegait.sigma.timesheet.enumerations.ProjectStatus;
import rs.vegait.sigma.timesheet.model.Project;
import rs.vegait.sigma.timesheet.repository.ProjectRepository;
import rs.vegait.sigma.timesheet.service.ProjectService;

@Service
public class JpaProjectService implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Optional<Project> one(Long id) {

		Optional<Project> project = projectRepository.findById(id);
		if (project.get().getIsDeleted() == false) {
			return project;
		} else {
			return null;
		}
	}

	@Override
	public List<Project> all() {

		List<Project> list = new ArrayList<>();
		var allProjects = projectRepository.findAll();

		for (Project project : allProjects) {
			if (project.getIsDeleted() == false) {
				list.add(project);
			}
		}

		return list;
	}

	@Override
	public Project save(Project project) {
		project.setStatus(ProjectStatus.ACTIVE);
		project.setIsDeleted(false);
		return projectRepository.save(project);
	}

	@Override
	public void delete(Long id) {
		Project deleted = projectRepository.getOne(id);
		deleted.setIsDeleted(true);
		projectRepository.save(deleted);

	}

	@Override
	public Optional<Project> findByName(String name) {
		return projectRepository.findByName(name);
	}

}
