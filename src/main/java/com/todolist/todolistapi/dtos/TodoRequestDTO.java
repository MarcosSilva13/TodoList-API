package com.todolist.todolistapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record TodoRequestDTO(
        @NotBlank(message = "O campo título não pode estar vazio.")
        String title,

        @NotBlank(message = "O campo descrição não pode estar vazio.")
        String description,

        String userId) {
}
