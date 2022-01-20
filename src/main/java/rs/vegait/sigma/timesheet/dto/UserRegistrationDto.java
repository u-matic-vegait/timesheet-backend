package rs.vegait.sigma.timesheet.dto;

import rs.vegait.sigma.timesheet.dto.UserDto;

public class UserRegistrationDto extends UserDto {
	private String password;
	private String passwordConfirm;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
