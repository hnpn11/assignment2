package com.fpt.assignment2.dto;

public class UserRegisterResponseDTO {
	private String username;
	private int userID;

	public UserRegisterResponseDTO(String username, int userID) {
		super();
		this.username = username;
		this.userID = userID;
	}

	public UserRegisterResponseDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
