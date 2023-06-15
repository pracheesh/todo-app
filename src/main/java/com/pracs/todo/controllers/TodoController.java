package com.pracs.todo.controllers;


import com.pracs.todo.models.Todo;
import com.pracs.todo.services.TodoService;
import com.pracs.todo.services.implementation.TodoServiceImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoServiceImple;
    Random random = new Random();

    //create
    @PostMapping()
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        //create TODO
//        String str = null;
//        logger.info("Size is: {}", str.length());
        logger.info("Create Todo");

        // set id
        int id = random.nextInt(9999999);
        todo.setId(id);

        //set system default date
        Date currentdate = new Date();

        logger.info("Current date: {}", currentdate);
//        todo.setAddedDate(currentdate);
        logger.info("Createing todo");

        //call service to create todo
        Todo todo1 = todoServiceImple.createTodo(todo);

        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //GET ALL TODO METHOD
     @GetMapping
     public ResponseEntity<List<Todo>> getAllTods(){


        List<Todo> allTodos = todoServiceImple.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
     }

     @GetMapping("/{todoId}")
     public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable("todoId") int todoId){

        Todo todo = todoServiceImple.getSingleTodo(todoId);
        return ResponseEntity.ok(todo);
     }

     //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int todoId, @RequestBody Todo updatedTodo){

        Todo newTodo = todoServiceImple.updateTodo(todoId, updatedTodo);
        return ResponseEntity.ok(newTodo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") int id){

        todoServiceImple.deleteTodo(id);
        return ResponseEntity.ok("Record deleted");
    }
}
