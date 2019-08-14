package com.cloud.tutorial.usersservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloud.tutorial.models.aggregates.AggregateUserBooks;

@Repository
public interface AggregateUsersBooksRepository extends CrudRepository<AggregateUserBooks, Long>{
	
	public Optional<AggregateUserBooks> findById(final Long id);
	public Optional<List<AggregateUserBooks>> findByName(final String name);
	public Optional<List<AggregateUserBooks>> findByLastname(final String lastName);
	public Optional<List<AggregateUserBooks>> findByAge(final Long age);
}
