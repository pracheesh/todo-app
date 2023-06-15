package com.pracs.todo.services;

import com.pracs.todo.models.Todo;

import java.util.List;

public interface TodoService {

    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodos();
    public Todo getSingleTodo(int todoId);
    public Todo updateTodo(int todoId, Todo updatedTodo);
    public void deleteTodo(int todoId);
}
