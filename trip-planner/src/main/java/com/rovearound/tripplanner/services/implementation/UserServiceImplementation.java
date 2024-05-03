package com.rovearound.tripplanner.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.repositories.UserRepository;
import com.rovearound.tripplanner.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedManager = this.userRepository.save(user);
		return this.userToDto(savedManager);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		user.setId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		
		User updatedUser = this.userRepository.save(user);
		UserDto updatedManagerDto = this.userToDto(updatedUser);
				
		return updatedManagerDto;
	}

	@Override
	public UserDto getUser(Integer userId) {
		User manager = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(manager);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		user.setStatus(false);
		this.userRepository.save(user);

	}
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setEmail(user.getEmail());
		user.setRole("traveler");
		user.setUserName(user.getUsername());
		user.setStatus(true);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}
	
	@Override
	public UserDto getUserByUsername(String username) {
	    return this.getAllUsers().stream()
	            .filter(user -> user.getUserName().equals(username))
	            .findFirst()
	            .orElse(null);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
