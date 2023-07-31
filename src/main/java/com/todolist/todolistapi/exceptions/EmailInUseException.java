package com.todolist.todolistapi.exceptions;

public class EmailInUseException extends RuntimeException {
    public EmailInUseException() {
        super();
    }

    public EmailInUseException(String message) {
        super(message);
    }
}
