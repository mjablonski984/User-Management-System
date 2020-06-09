package com.urs;

import com.urs.models.User;
import com.urs.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrsApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	// Override run method and create admin account
	@Override
	public void run(String... args) throws Exception {
		{
			User newAdmin = new User("admin@mail.com", "Admin", "password");
			userService.createAdmin(newAdmin);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(UrsApplication.class, args);
	}

}
