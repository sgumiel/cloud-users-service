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

import com.cloud.tutorial.models.aggregates.AggregateUserBooks;
import com.cloud.tutorial.usersservice.repositories.AggregateUsersBooksRepository;

@RestController
public class AggregateUsersBooksController {

	private static final Logger logger = LoggerFactory.getLogger(AggregateUsersBooksController.class);
	
	@Autowired
	private AggregateUsersBooksRepository aggregateUsersBooksRepository;
	
	@GetMapping("/users-books")
	public List<AggregateUserBooks> getUsers() {
		
		logger.debug("Request for all aggregated users books");
		
		final List<AggregateUserBooks> usersList = (List)aggregateUsersBooksRepository.findAll();
		
		logger.debug("Aggregated users books returned");

		return usersList;
	}

	@GetMapping("/users-books/{id}")
	public AggregateUserBooks findByIds(@PathVariable @NotNull @DecimalMin("1") Long id) {

		logger.debug("Request for the aggregated user books with id: {}", id);

		final Optional<AggregateUserBooks> opUser = aggregateUsersBooksRepository.findById(id);

		AggregateUserBooks user = null;
		if (opUser.isPresent()) {
			user = opUser.get();
			logger.debug("Aggregated user books found: {}", id);
		}
		
		return user;

	}

}