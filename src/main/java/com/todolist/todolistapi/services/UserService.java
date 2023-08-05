package com.todolist.todolistapi.services;

import com.todolist.todolistapi.dtos.UserResponseDTO;
import com.todolist.todolistapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //só para testes
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }
}
