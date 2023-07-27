package com.todolist.todolistapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDTO(
        @NotBlank(message = "Nome de usuário inválido!")
        String name,

        @NotBlank(message = "Email inválido!")
        @Email(message = "Email com formato inválido!")
        String email,

        @NotBlank(message = "Senha inválida!")
        String password) {
}
