package com.todolist.todolistapi.services;

import com.todolist.todolistapi.dtos.TodoRequestDTO;
import com.todolist.todolistapi.dtos.TodoResponseDTO;
import com.todolist.todolistapi.entities.Todo;
import com.todolist.todolistapi.entities.User;
import com.todolist.todolistapi.enums.Status;
import com.todolist.todolistapi.exceptions.TodoNotFoundException;
import com.todolist.todolistapi.repositories.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDTO> getAllByUserId(String status) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Status statusEnum = Status.PENDENTE;

        if (status.equals("completed")) {
            statusEnum = Status.CONCLUIDA;
        }

        return todoRepository.findByUserIdAndStatusEqual(user.getId(), statusEnum,
                        Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(TodoResponseDTO::new)
                .toList();
    }

    @Transactional
    public TodoResponseDTO save(TodoRequestDTO todoRequestDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Todo todo = new Todo(todoRequestDTO, user);
        todo.setStatus(Status.PENDENTE);
        todo.setCreatedAt(LocalDateTime.now());

        Todo todoSaved = todoRepository.save(todo);

        return new TodoResponseDTO(todoSaved);
    }

    @Transactional
    public TodoResponseDTO update(String id, TodoRequestDTO todoRequestDTO) {
        Todo todo = this.findTodo(id);

        BeanUtils.copyProperties(todoRequestDTO, todo);

        Todo todoUpdated = todoRepository.save(todo);

        return new TodoResponseDTO(todoUpdated);
    }

    @Transactional
    public TodoResponseDTO updateStatusToCompleted(String id) {
        Todo todo = this.findTodo(id);
        todo.setStatus(Status.CONCLUIDA);

        Todo todoUpdated = todoRepository.save(todo);

        return new TodoResponseDTO(todoUpdated);
    }

    @Transactional
    public void delete(String id) {
        Todo todo = this.findTodo(id);

        todoRepository.delete(todo);
    }

    private Todo findTodo(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Tarefa não encontrada!"));
    }
}