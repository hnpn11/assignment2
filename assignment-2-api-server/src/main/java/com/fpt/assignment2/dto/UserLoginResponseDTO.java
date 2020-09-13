package com.fpt.assignment2.dto;

public class UserLoginResponseDTO {
	private String username;
	private String token;

	public UserLoginResponseDTO(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public UserLoginResponseDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
