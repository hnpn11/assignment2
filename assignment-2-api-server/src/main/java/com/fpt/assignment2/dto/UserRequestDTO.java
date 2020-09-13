package com.fpt.assignment2.dto;

import org.hibernate.validator.constraints.NotBlank;

public class UserRequestDTO {
	@NotBlank
	private String username;

	@NotBlank
	private String userpass;

	public UserRequestDTO(String username, String userpass) {
		super();
		this.username = username;
		this.userpass = userpass;
	}

	public UserRequestDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

}
