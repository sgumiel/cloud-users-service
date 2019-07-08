package com.cloud.tutorial.usersservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.cloud.tutorial.usersservice.models.User;
import com.cloud.tutorial.usersservice.repositories.UsersRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersServiceApplication implements CommandLineRunner{
	
	@Autowired
	private UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		
		u1.setName("Sergio");
		u1.setLastName("Gumiel");
		u1.setAge(36);
		
		u2.setName("Nuria");
		u2.setLastName("Quesada");
		u2.setAge(28);
		
		u3.setName("Chache");
		u3.setLastName("Suker");
		u3.setAge(41);
		
		usersRepository.save(u1);
		usersRepository.save(u2);
		usersRepository.save(u3);
	}

}
