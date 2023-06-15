package com.pracs.todo.dao;

import com.pracs.todo.helper.Helper;
import com.pracs.todo.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<Todo> {


    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();

        todo.setId((int) (rs.getInt("id")));
        todo.setTitle((String) (rs.getString("title")));
        todo.setContent((String) (rs.getString("content")));
        todo.setStatus((String) (rs.getString("status")));
        todo.setAddedDate(Helper.parseDate((LocalDateTime) (rs.getObject("addedDate"))));
        todo.setToBeDoneDate(Helper.parseDate((LocalDateTime) (rs.getObject("toBeDoneDate"))));

        return todo;
    }
}
