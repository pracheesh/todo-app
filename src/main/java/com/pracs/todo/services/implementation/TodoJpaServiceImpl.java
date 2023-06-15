package com.pracs.todo.services.implementation;

import com.pracs.todo.dao.TodoRepository;
import com.pracs.todo.exceptions.ResourceNotFoundException;
import com.pracs.todo.models.Todo;
import com.pracs.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TodoJpaServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Override
    public Todo createTodo(Todo todo) {
        Todo saveTodo = todoRepository.save(todo);

        return saveTodo;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getSingleTodo(int todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("No todo with the given ID", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo updatedTodo) {
        Todo oldTodo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("No todo with the given ID", HttpStatus.NOT_FOUND));
        oldTodo.setTitle(updatedTodo.getTitle());
        oldTodo.setContent(updatedTodo.getContent());
        oldTodo.setStatus(updatedTodo.getStatus());
//        oldTodo.setAddedDate("");
        oldTodo.setToBeDoneDate(updatedTodo.getToBeDoneDate());
        return todoRepository.save(oldTodo);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Resource with given id not present", HttpStatus.NOT_FOUND));
        todoRepository.delete(todo);
    }
}
