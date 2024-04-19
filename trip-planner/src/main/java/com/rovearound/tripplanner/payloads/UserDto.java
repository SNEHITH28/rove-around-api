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

}