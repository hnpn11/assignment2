package com.fpt.assignment2.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.assignment2.ErrorMessage;
import com.fpt.assignment2.CustomMessageError;
import com.fpt.assignment2.dto.UserRequestDTO;
import com.fpt.assignment2.dto.UserLoginResponseDTO;
import com.fpt.assignment2.dto.UserRegisterResponseDTO;
import com.fpt.assignment2.entity.User;
import com.fpt.assignment2.repository.UserRepository;
import com.fpt.assignment2.service.TokenService;
import com.fpt.assignment2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	private BCryptPasswordEncoder crypt = new BCryptPasswordEncoder(10);

	@Override
	public UserRegisterResponseDTO save(UserRequestDTO userRequestDTO) throws CustomMessageError {
		if (!userRequestDTO.getUserpass().matches("^[a-zA-z]([a-zA-z0-9!@#$%^&*~_])*$")) {
			throw new CustomMessageError(new ErrorMessage("Field is invalid"), HttpStatus.BAD_REQUEST);
		}
		if (!checkUserExist(userRequestDTO)) {
			
			userRequestDTO.setUserpass(crypt.encode(userRequestDTO.getUserpass()));
			User user = userRepository.save(changeUserResquestDTO2User(userRequestDTO));
			return new UserRegisterResponseDTO(user.getUsername(), user.getUserId());
		} else {
			throw new CustomMessageError(new ErrorMessage("User Existed"), HttpStatus.CONFLICT);
		}
	}

	@Override
	public UserRegisterResponseDTO findByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return changeUser2UserResponeDTO(user);
	}
	
	@Override
	public UserLoginResponseDTO login(UserRequestDTO userRequestDTO) throws CustomMessageError {
		if (!userRequestDTO.getUserpass().matches("^[a-zA-z]([a-zA-z0-9!@#$%^&*~_])*$")) {
			throw new CustomMessageError(new ErrorMessage("Field is invalid"), HttpStatus.BAD_REQUEST);
		}
		if (checkUserExist(userRequestDTO)) {
			User user = userRepository.findUserByUsername(userRequestDTO.getUsername());
			if (crypt.matches(userRequestDTO.getUserpass(), user.getUserpass())) {
				String username = user.getUsername();
				String token 	= tokenService.getJWTToken(user.getUsername());
				return new UserLoginResponseDTO(username, token);
			} else {
				throw new CustomMessageError(new ErrorMessage("Unauthenticated"),HttpStatus.UNAUTHORIZED);
			}
		} else {
			throw new CustomMessageError(new ErrorMessage("Unauthenticated"), HttpStatus.NOT_FOUND);
		}
	}

	private UserRegisterResponseDTO changeUser2UserResponeDTO(User user) {
		return new UserRegisterResponseDTO(user.getUsername(), user.getUserId());
	}

	private boolean checkUserExist(UserRequestDTO userRequestDTO) {
		User user = userRepository.findUserByUsername(userRequestDTO.getUsername());
		if (null != user) {
			return true;
		} else {
			return false;
		}
	}

	private User changeUserResquestDTO2User(UserRequestDTO userRequestDTO) {
		return new User(userRequestDTO.getUsername(), userRequestDTO.getUserpass());
	}

	
}
