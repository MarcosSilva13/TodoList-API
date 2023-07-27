package com.todolist.todolistapi.dtos;

import com.todolist.todolistapi.entities.Todo;
import com.todolist.todolistapi.enums.Status;

public record TodoResponseDTO(String id, String title, String description, Status status,
                              UserResponseDTO user) {

    public TodoResponseDTO(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription(),
                todo.getStatus(), new UserResponseDTO(todo.getUser()));
    }
}
