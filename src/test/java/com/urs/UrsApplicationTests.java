package com.urs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.urs.services.TaskService;
import com.urs.services.UserService;
import com.urs.models.Task;
import com.urs.models.User;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class UrsApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@BeforeAll
	public void initDb() {
		{
			User newUser = new User("user@test.com", "testUser", "password");
			userService.createUser(newUser);
		}
		{
			User newUser = new User("admin@test.com", "testAdmin", "password");
			userService.createAdmin(newUser);
		}

		Task userTask = new Task("20/05/2020", "08:30", "16:03", "Training day");
		User user = userService.findOne("user@test.com");
		taskService.addTask(userTask, user);
	}

	@Test
	public void testUser() {
		User user = userService.findOne("user@test.com");
		assertNotNull(user);
		User admin = userService.findOne("admin@test.com");
		assertEquals(admin.getEmail(), "admin@test.com");
	}

	@Test
	public void testTask() {
		User user = userService.findOne("testUser@mail.com");
		List<Task> task = taskService.findUserTask(user);
		assertNotNull(task);
	}

}
