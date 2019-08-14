package com.cloud.tutorial.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloud.tutorial.models.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User>{
	
}
