package rs.vegait.sigma.timesheet.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ProjectDto;
import rs.vegait.sigma.timesheet.model.Project;
import rs.vegait.sigma.timesheet.service.ProjectService;

@Component
public class ProjectToProjectDto implements Converter<Project, ProjectDto> {

	@Autowired
	private ProjectService projectService;

	@Override
	public ProjectDto convert(Project source) {
		ProjectDto target = new ProjectDto();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setStatus(source.getStatus());
		target.setIsDeleted(source.getIsDeleted());

		return target;
	}

}
