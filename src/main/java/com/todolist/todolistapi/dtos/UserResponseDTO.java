package com.todolist.todolistapi.dtos;

import com.todolist.todolistapi.entities.User;

public record UserResponseDTO(String id, String name, String email) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
