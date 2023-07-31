package com.todolist.todolistapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDTO(
        @NotBlank(message = "O campo nome não pode estar vazio.")
        String name,

        @NotBlank(message = "O campo email não pode estar vazio.")
        @Email(message = "Email com formato inválido!")
        String email,

        @NotBlank(message = "O campo senha não pode estar vazio.")
        String password) {
}
