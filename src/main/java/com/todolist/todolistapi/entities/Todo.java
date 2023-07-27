package com.todolist.todolistapi.entities;

import com.todolist.todolistapi.dtos.TodoRequestDTO;
import com.todolist.todolistapi.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo() {
    }

    public Todo(String id, String title, String description, Status status, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public Todo(TodoRequestDTO todoRequestDTO) {
        this.title = todoRequestDTO.title();
        this.description = todoRequestDTO.description();
        this.user = new User(todoRequestDTO.userId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}