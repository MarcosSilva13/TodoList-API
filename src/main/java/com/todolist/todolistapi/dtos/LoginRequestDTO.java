package com.todolist.todolistapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "O campo email não pode estar vazio.")
        @Email(message = "Email com formato inválido!")
        String email,

        @NotBlank(message = "O campo senha não pode estar vazio.")
        String password) {
}
