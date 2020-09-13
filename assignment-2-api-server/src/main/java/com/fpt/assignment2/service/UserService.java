package com.fpt.assignment2.service;


import com.fpt.assignment2.CustomMessageError;
import com.fpt.assignment2.dto.UserRequestDTO;
import com.fpt.assignment2.dto.UserLoginResponseDTO;
import com.fpt.assignment2.dto.UserRegisterResponseDTO;

public interface UserService {

	UserRegisterResponseDTO save(UserRequestDTO userRequestDTO) throws CustomMessageError;
	
	UserRegisterResponseDTO findByUsername(String username);
	
	UserLoginResponseDTO login(UserRequestDTO userRequestDTO) throws CustomMessageError;
}
