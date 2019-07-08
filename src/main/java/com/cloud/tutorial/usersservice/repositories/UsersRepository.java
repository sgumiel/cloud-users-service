package com.cloud.tutorial.usersservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloud.tutorial.usersservice.models.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findById(final Long id);
	public Optional<List<User>> findByName(final String name);
	public Optional<List<User>> findByLastName(final String lastName);
	public Optional<List<User>> findByAge(final Long age);

}
