package com.cloud.tutorial.usersservice.controllers;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.tutorial.models.User;
import com.cloud.tutorial.usersservice.advancedsearch.SearchOperation;
import com.cloud.tutorial.usersservice.advancedsearch.specifications.UserSpecificationsBuilder;
import com.cloud.tutorial.usersservice.repositories.UsersRepository;
import com.google.common.base.Joiner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UsersController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		
		log.debug("Request for all aggregated users books");
		
		final List<User> usersList = (List)usersRepository.findAll();
		
		log.debug("Aggregated users books returned");

		return usersList;
	}
	
	@GetMapping("/users/search")
	public List<User> getUserByEmail(@RequestParam(name = "search") String search){
		
		UserSpecificationsBuilder userSpecificationBuilder = new UserSpecificationsBuilder();
		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		Pattern pattern = Pattern.compile(
			      "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
	    Matcher matcher = pattern.matcher(search + ",");
	    while (matcher.find()) {
	    	userSpecificationBuilder.with(
	          matcher.group(1), 
	          matcher.group(2), 
	          matcher.group(4), 
	          matcher.group(3), 
	          matcher.group(5));
	    }
	 
	    Specification<User> spec = userSpecificationBuilder.build();
	    return usersRepository.findAll(spec);
		
	}
	

	@GetMapping("/users/{id}")
	public User findByIds(@PathVariable @NotNull @DecimalMin("1") Long id) {

		log.debug("Request for the aggregated user books with id: {}", id);

		final Optional<User> opUser = usersRepository.findById(id);

		User user = null;
		if (opUser.isPresent()) {
			user = opUser.get();
			log.debug("User found: {}", id);
		}
		
		return user;

	}
	
	@PostMapping("/users")
	public User createUSer(@RequestBody User user) {
		
		log.debug("Request for a new User: {}", user);
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		final User userCreated = this.usersRepository.save(user);
		
		log.debug("User created");
		
		return userCreated;
		
		
		
	}
}