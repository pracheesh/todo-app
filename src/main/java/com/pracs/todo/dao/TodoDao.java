package com.pracs.todo.dao;

import com.pracs.todo.helper.Helper;
import com.pracs.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {


    private JdbcTemplate template;
    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        String createTable = "create table IF NOT EXISTS todos(id int primary key, title varchar(100) not null, content varchar(500), status varchar(10) not null, addedDate datetime, toBeDoneDate datetime)";
        int update = template.update(createTable);
        logger.info("Todo Table created: {}", update);
    }


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
     public Todo saveTodo(Todo todo){

        String insertquery = "insert into todos(id, title, content, status, addedDate, toBeDoneDate) values(?, ?, ?, ?, ?, ?)";
        int rows = template.update(insertquery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getToBeDoneDate());
        logger.info("JDBC Operation: {} inserted", rows);
        return todo;

     }

     //get single todo
    public Todo getSingleTodo(int id){
        String query = "select * from todos WHERE id=?";
//        Map<String, Object> todoData = template.queryForMap(query, id);
        Todo todo = template.queryForObject(query, new TodoRowMapper(), id);
        logger.info("Todo Data: {}", todo);
        return todo;
//        Todo todo = new Todo();
//
//        todo.setId((int)(todoData.get("id")));
//        todo.setTitle((String)(todoData.get("title")));
//        todo.setContent((String)(todoData.get("content")));
//        todo.setStatus((String) (todoData.get("status")));
//        todo.setAddedDate(Helper.parseDate((LocalDateTime) (todoData.get("addedDate"))));
//        todo.setToBeDoneDate(Helper.parseDate((LocalDateTime) (todoData.get("toBeDoneDate"))));
//        logger.info("Single todo: {}", todo);
//        return todo;

    }

    // get all todos

    public List<Todo> getAllTodos(){
        String query = "select * from todos";
//        List<Map<String, Object>> todoMaps = template.queryForList(query);
        List<Todo> todos = template.query(query, new TodoRowMapper());

//        List<Todo> todos = todoMaps.stream().map((map) -> {
//            Todo todo = new Todo();
//
//            todo.setId((int) (map.get("id")));
//            todo.setTitle((String) (map.get("title")));
//            todo.setContent((String) (map.get("content")));
//            todo.setStatus((String) (map.get("status")));
//            todo.setAddedDate(Helper.parseDate((LocalDateTime) (map.get("addedDate"))));
//            todo.setToBeDoneDate(Helper.parseDate((LocalDateTime) (map.get("toBeDoneDate"))));
//
//            return todo;
//
//        }).collect(Collectors.toList());
        logger.info("Todo Data: {}", todos);
        return todos;
    }

    // update todos when id is given
    public Todo updateTodo(int id, Todo newTodo){

        String query = "update todos set title=?, content=?, status=?, addedDate=?, toBeDoneDate=? WHERE id=?";
        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getToBeDoneDate(), id);
        logger.info("Updated: {}", update);
        newTodo.setId(id);
        return newTodo;

    }

    // delete todos
    public void deleteTodo(int id){

        String query = "delete from todos WHERE id=?";
        int update = template.update(query, id);
        logger.info("Deleted {}", update);
    }

    // delete multiple todos
    public void deleteMultipleTodos(int[] ids){
        String query = "delete from todos WHERE id=?";

        // the anonymous class setValue function updates the id value for each query and batchUpdate runs the query for all updates at once
        template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for(int i : ids){
            logger.info("Deleted {}", i);
        }
    }
}
