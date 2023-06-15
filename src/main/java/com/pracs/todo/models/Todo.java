package com.pracs.todo.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*
class to store the data of a todo list
 */
@Entity
@Table(name = "jpa_todos")
public class Todo {

    @Id
    private int id;
    @Column(name = "todo_title")
    private String title;

    @Column(name = "todo_content")
    private String content;

    @Column(name = "todo_status", length = 10)
    private String status;

    @Column(name = "todo_addedDate")
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date addedDate;

    @Column(name = "todo_toBeDoneDate")
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date toBeDoneDate;

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getToBeDoneDate() {
        return toBeDoneDate;
    }

    public void setToBeDoneDate(Date toBeDoneDate) {
        this.toBeDoneDate = toBeDoneDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addedDate=" + addedDate +
                ", toBeDoneDate=" + toBeDoneDate +
                '}';
    }

    public Todo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
