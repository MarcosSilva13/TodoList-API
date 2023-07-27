package com.todolist.todolistapi.exceptions;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException() {
        super();
    }

    public TodoNotFoundException(String message) {
        super(message);
    }
}
