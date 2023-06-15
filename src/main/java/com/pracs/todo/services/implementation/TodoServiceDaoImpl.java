package com.pracs.todo.services.implementation;

import com.pracs.todo.dao.TodoDao;
import com.pracs.todo.models.Todo;
import com.pracs.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class TodoServiceDaoImpl implements TodoService {

    @Autowired
    TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getSingleTodo(int todoId) {
        return todoDao.getSingleTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo updatedTodo) {
        return todoDao.updateTodo(todoId, updatedTodo);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
