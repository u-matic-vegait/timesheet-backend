package rs.vegait.sigma.timesheet.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ProjectDto;
import rs.vegait.sigma.timesheet.model.Project;
import rs.vegait.sigma.timesheet.service.ProjectService;

@Component
public class ProjectDtoToProject implements Converter<ProjectDto, Project> {

	@Autowired
	private ProjectService projectService;

	@Override
	public Project convert(ProjectDto source) {
		Project target = null;
		if (source.getId() != null) {
			target = projectService.findOne(source.getId()).get();
		}

		if (target == null) {
			target = new Project();
		}

		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setStatus(source.getStatus());
		target.setIsDeleted(source.getIsDeleted());

		return target;
	}

}
