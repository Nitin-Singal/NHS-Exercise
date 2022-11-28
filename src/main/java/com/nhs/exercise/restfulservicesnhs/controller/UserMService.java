package com.nhs.exercise.restfulservicesnhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.nhs.exercise.restfulservicesnhs.dao.UserRepository;
import com.nhs.exercise.restfulservicesnhs.exception.UserNotFoundException;
import com.nhs.exercise.restfulservicesnhs.model.UserBean;

import jakarta.validation.Valid;

/**
 * @author Nitin 
 * RestService to expose API endpoints for maintaining Users
 *
 */
@RestController
public class UserMService {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "nhsService/getAllUsers")
	public List<UserBean> getAllUsers() {
		List<UserBean> userList = userRepository.findAll();
		return userList;
	}

	@GetMapping(path = "nhsService/getUser/{id}")
	public UserBean retrieveUser(@PathVariable long id) {
		UserBean user = userRepository.findById(id).orElse(null);

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

	@PostMapping(path = "nhsService/createUser")
	public ResponseEntity<UserBean> createUser(@Valid @RequestBody UserBean user) {
		if (user.getId() != null) {
			UserBean checkUser = userRepository.findById(user.getId()).orElse(null);
			if (checkUser != null) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User already present");
			}
		}
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping(path = "nhsService/deleteUser/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}

	@PostMapping(path = "nhsService/updateUser")
	public void updateUser(@Valid @RequestBody UserBean user) {
		UserBean userBean = userRepository.findById(user.getId()).orElse(null);

		if (userBean == null) {
			throw new UserNotFoundException("User not found to be modified");
		}
		userRepository.save(user);
	}
}
