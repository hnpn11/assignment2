package com.fpt.assignment2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.assignment2.CustomMessageError;
import com.fpt.assignment2.dto.UserRequestDTO;
import com.fpt.assignment2.dto.UserLoginResponseDTO;
import com.fpt.assignment2.dto.UserRegisterResponseDTO;
import com.fpt.assignment2.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity<UserRegisterResponseDTO> regiter(@Valid @RequestBody UserRequestDTO userRequestDTO) throws CustomMessageError {
		UserRegisterResponseDTO response = userService.save(userRequestDTO);;
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserRequestDTO userRequestDTO) throws CustomMessageError {
		UserLoginResponseDTO response = userService.login(userRequestDTO);
		return ResponseEntity.ok().body(response);
	}

}
