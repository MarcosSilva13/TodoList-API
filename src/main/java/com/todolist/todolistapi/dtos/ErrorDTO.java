package com.todolist.todolistapi.dtos;

public record ErrorDTO(String title, Integer status, String detail, String instance, String timestamp) {
}
