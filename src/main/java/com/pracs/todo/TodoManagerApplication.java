package com.pracs.todo;

import com.pracs.todo.dao.TodoDao;
import com.pracs.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@SpringBootApplication
public class TodoManagerApplication implements ApplicationRunner{


	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);
	@Autowired
	TodoDao todoDao;

	public static void main(String[] args) {

		SpringApplication.run(TodoManagerApplication.class, args);
		System.out.println("check");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("Template object: {}", todoDao.getTemplate());
//		logger.info("Template Object: {}", template.getDataSource());
//
//		Todo todo = new Todo();
//		todo.setId(8);
//		todo.setTitle("My day plan");
//		todo.setContent("Finish CICD for tm");
//		todo.setStatus("InProgress");
//		todo.setAddedDate(new Date());
//		todo.setToBeDoneDate(new Date());
//
//		todoDao.saveTodo(todo);

//		Todo updatetodo = new Todo();
//		updatetodo.setTitle("Learning Springboot");
//		updatetodo.setContent("Finish Spring JDBC by tomorrow");
//		updatetodo.setAddedDate(new Date());
//		updatetodo.setToBeDoneDate(new Date());
//		updatetodo.setStatus("AlmostDone");
//		todoDao.getSingleTodo(2);
//		todoDao.updateTodo(7, updatetodo);
//		todoDao.getAllTodos();
//		todoDao.deleteTodo(4);
//		todoDao.deleteMultipleTodos(new int[]{6, 7, 8});
	}
}
