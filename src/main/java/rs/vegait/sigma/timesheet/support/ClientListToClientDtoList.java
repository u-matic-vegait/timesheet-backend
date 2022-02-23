package rs.vegait.sigma.timesheet.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.vegait.sigma.timesheet.dto.ProjectDto;
import rs.vegait.sigma.timesheet.model.Project;

@Component
public class ClientListToClientDtoList implements Converter<List<Project>, List<ProjectDto>> {

	@Autowired
	private ProjectToProjectDto toDto;

	@Override
	public List<ProjectDto> convert(List<Project> source) {
		List<ProjectDto> target = new ArrayList<>();

		for (Project project : source) {
			ProjectDto dto = toDto.convert(project);
			target.add(dto);
		}

		return target;
	}

}
