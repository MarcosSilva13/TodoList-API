package com.todolist.todolistapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record TodoRequestDTO(
        @NotBlank(message = "Título inválido!")
        String title,

        @NotBlank(message = "Descrição inválida!")
        String description,

        String userId) {
}
