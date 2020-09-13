package com.fpt.assignment2.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userID;

	@NotBlank
	@Size(min = 5, max = 15)
	@Pattern(regexp = "^[a-zA-z]([a-zA-z0-9])*$")
	private String username;

	@NotBlank
	private String userpass;

	@OneToMany(mappedBy = "user")
	private Set<FileManagement> lstFile = new HashSet<>();
	
	public User() {
		super();
	}

	public User(int userId, String username, String userpass) {
		super();
		this.userID = userId;
		this.username = username;
		this.userpass = userpass;
	}

	public User(int userID, String username, String userpass, Set<FileManagement> lstFile) {
		super();
		this.userID = userID;
		this.username = username;
		this.userpass = userpass;
		this.lstFile = lstFile;
	}

	public User(String username, String userpass) {
		super();
		this.username = username;
		this.userpass = userpass;
	}

	public int getUserId() {
		return userID;
	}

	public void setUserId(int userId) {
		this.userID = userId;
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

	public Set<FileManagement> getLstFile() {
		return lstFile;
	}

	public void setLstFile(Set<FileManagement> lstFile) {
		this.lstFile = lstFile;
	}

}
