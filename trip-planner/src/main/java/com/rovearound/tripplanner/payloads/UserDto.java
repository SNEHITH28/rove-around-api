package com.rovearound.tripplanner.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int userId;
	
	@NotEmpty(message = "Please enter first name")
	@Size(min = 3, max = 20, message = "Username must be in between 3 and 20 characters long")
	private String userName;
	
	@Email
	private String email;

	@NotEmpty(message = "Please enter password")
	@Size(min = 6, max = 15, message = "Password must be in between 6 and 15 characters long")
	private String password;
	
	private String role;
	
	private boolean status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}