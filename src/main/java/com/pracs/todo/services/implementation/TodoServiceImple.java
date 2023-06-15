package com.pracs.todo.services.implementation;

import com.pracs.todo.exceptions.ResourceNotFoundException;
import com.pracs.todo.models.Todo;
import com.pracs.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImple implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImple.class);

    // list carrying all todos
    List<Todo> todos = new ArrayList<>();

    //create todo
    public Todo createTodo(Todo todo){


        // change the logic if data is to be stored in database
        todos.add(todo);
        logger.info("Todos: {}", this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {

        return todos;
    }

    public Todo getSingleTodo(int todoId) {

//        Todo todo = todos.stream().filter(t -> todoId == t.getId()).findAny().get();
        // with exception handling
        Todo todo = todos.stream().filter(t -> todoId == t.getId()).findAny().orElseThrow(() -> new ResourceNotFoundException("TODO not found with given ID", HttpStatus.NOT_FOUND));
        logger.info("Todo: {}", todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo updatedTodo) {

        List<Todo> newUpdatedList = todos.stream().map(t -> {
            if(t.getId() == todoId){
                // perform update
                t.setContent(updatedTodo.getContent());
                t.setStatus(updatedTodo.getStatus());
                t.setTitle(updatedTodo.getTitle());
                return t;
            }
            else{
                return t;
            }
        }).collect(Collectors.toList());

        todos = newUpdatedList;
        updatedTodo.setId(todoId);
        return updatedTodo;
    }

    public void deleteTodo(int todoId) {
        List<Todo> newList = todos.stream().filter(t -> t.getId()!=todoId).collect(Collectors.toList());
        todos = newList;
    }
}
