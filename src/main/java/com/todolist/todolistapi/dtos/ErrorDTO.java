package com.todolist.todolistapi.dtos;

public record ErrorDTO(String message, Integer status, String error, String timestamp) {
}
