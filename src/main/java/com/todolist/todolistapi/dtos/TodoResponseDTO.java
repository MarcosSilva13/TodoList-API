package com.todolist.todolistapi.dtos;

import com.todolist.todolistapi.entities.Todo;
import com.todolist.todolistapi.enums.Status;

import java.time.LocalDateTime;

public record TodoResponseDTO(String id, String title, String description, Status status, LocalDateTime createdAt) {

    public TodoResponseDTO(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getCreatedAt());
    }
}
