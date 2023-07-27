package com.todolist.todolistapi.controllers;

import com.todolist.todolistapi.dtos.TodoRequestDTO;
import com.todolist.todolistapi.dtos.TodoResponseDTO;
import com.todolist.todolistapi.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos(@PathVariable String id) {
        return ResponseEntity.ok().body(todoService.getAllByUserId(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> saveTodo(@RequestBody TodoRequestDTO todoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(todoRequestDTO));
    }
}
