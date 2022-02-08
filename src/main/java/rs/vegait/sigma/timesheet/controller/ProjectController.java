package rs.vegait.sigma.timesheet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.vegait.sigma.timesheet.dto.ProjectDto;
import rs.vegait.sigma.timesheet.model.Project;
import rs.vegait.sigma.timesheet.service.ProjectService;

@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Converter<Project, ProjectDto> toDto;

	@Autowired
	private Converter<ProjectDto, Project> toProject;

	@Autowired
	private Converter<List<Project>, List<ProjectDto>> toDtoList;

	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> get(@PathVariable Long id) {
		Optional<Project> project = projectService.one(id);

		if (project == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		if (project.isPresent()) {
			ProjectDto body = toDto.convert(project.get());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
			System.out.println(reqBody);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Project toAdd = toProject.convert(reqBody);

		Project persisted = projectService.save(toAdd);

		ProjectDto respBody = toDto.convert(persisted);
		return new ResponseEntity<>(respBody, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Optional<Project> project = projectService.one(id);

		if (project != null) {
			if (project.get().getIsDeleted() == false) {
				projectService.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<ProjectDto> edit(@RequestBody ProjectDto projectDto) {

		Optional<Project> project = projectService.one(projectDto.getId());

		if (project == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Project toSave = toProject.convert(projectDto);
		Project persisted = projectService.save(toSave);

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
