package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUser(Integer userId);
	UserDto getUserByUsername(String username);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
