package rs.vegait.sigma.timesheet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import rs.vegait.sigma.timesheet.enumerations.ProjectStatus;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String description;

	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	@Column
	private Boolean isdeleted;

	public Project() {

	}

	public Project(Long id, String name, String description, ProjectStatus status, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.isdeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isdeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isdeleted = isDeleted;
	}

}
