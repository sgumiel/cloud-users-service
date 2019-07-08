package com.cloud.tutorial.usersservice.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.tutorial.usersservice.models.User;
import com.cloud.tutorial.usersservice.repositories.UsersRepository;

@RestController
public class UsersController {

	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/users")
	public List<User> getUsers() {

		logger.debug("Request for all users");
		
		final List<User> usersList = (List)usersRepository.findAll();
		
		logger.debug("Users returned");

		return usersList;
	}

	@GetMapping("/users/{id}")
	public User findByIds(@PathVariable @NotNull @DecimalMin("1") Long id) {

		logger.debug("Request for the user with id: {}", id);

		final Optional<User> opUser = usersRepository.findById(id);

		User user = null;
		if (opUser.isPresent()) {
			user = opUser.get();
			logger.debug("User found: {}", id);
		}
		
		return user;

	}

}