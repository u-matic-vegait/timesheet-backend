package rs.vegait.sigma.timesheet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.vegait.sigma.timesheet.dto.ProjectDto;
import rs.vegait.sigma.timesheet.exception.ResourceNotFoundException;
import rs.vegait.sigma.timesheet.model.Project;
import rs.vegait.sigma.timesheet.service.ProjectService;

@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

	private ProjectService projectService;
	private Converter<Project, ProjectDto> toDto;
	private Converter<ProjectDto, Project> toProject;
	private Converter<List<Project>, List<ProjectDto>> toDtoList;

	public ProjectController(ProjectService projectService, Converter<Project, ProjectDto> toDto,
			Converter<ProjectDto, Project> toProject, Converter<List<Project>, List<ProjectDto>> toDtoList) {
		this.projectService = projectService;
		this.toDto = toDto;
		this.toProject = toProject;
		this.toDtoList = toDtoList;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> get(@PathVariable Long id) {
//		Optional<Project> project = projectService.findOne(id);
//
//		if (!project.isPresent() || project.get().getIsDeleted() == true)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//		ProjectDto body = toDto.convert(project.get());
//		return new ResponseEntity<>(body, HttpStatus.OK);

		Project project = projectService.findOne(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + id));

		ProjectDto body = toDto.convert(project);

		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<ProjectDto>> getAll() {

		List<Project> clients = projectService.all();
		List<ProjectDto> body = toDtoList.convert(clients);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ProjectDto> add(@RequestBody @Validated ProjectDto reqBody) {

		if (reqBody.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Project toAdd = toProject.convert(reqBody);

		Project persisted = projectService.save(toAdd);

		ProjectDto respBody = toDto.convert(persisted);
		return new ResponseEntity<>(respBody, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Optional<Project> project = projectService.findOne(id);

		if (!project.isPresent() || project.get().getIsDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		projectService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<ProjectDto> edit(@RequestBody ProjectDto projectDto) {

		Optional<Project> project = projectService.findOne(projectDto.getId());

		if (project == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Project toSave = toProject.convert(projectDto);
		Project persisted = projectService.save(toSave);

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);

	}

}
