package com.rovearound.tripplanner.controllers;


import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ApiException;
import com.rovearound.tripplanner.payloads.JwtAuthRequest;
import com.rovearound.tripplanner.payloads.JwtAuthResponse;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.repositories.UserRepository;
import com.rovearound.tripplanner.security.JwtTokenHelper;
import com.rovearound.tripplanner.security.SecureUserService;
import com.rovearound.tripplanner.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private SecureUserService secureUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = this.secureUserService.loadUserByUsername(request.getEmail());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto managerDto) {
		if(this.userRepository.findByEmail(managerDto.getEmail()).isEmpty()) {
			UserDto registeredUser = this.userService.registerNewUser(managerDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED.value());
	    }
	    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(HttpStatus.ALREADY_REPORTED.value());
	}

	// get loggedin user data
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<UserDto> getUser(Principal principal) {
		User user = this.userRepository.findByEmail(principal.getName()).get();
		return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<JwtAuthResponse> deleteToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }
        
        JwtAuthResponse returnResponse = new JwtAuthResponse();
		
		returnResponse.setToken(null);
		
		return new ResponseEntity<JwtAuthResponse>(returnResponse, HttpStatus.OK);
	}

}