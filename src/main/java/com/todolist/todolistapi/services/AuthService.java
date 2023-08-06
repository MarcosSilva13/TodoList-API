package com.todolist.todolistapi.services;

import com.todolist.todolistapi.dtos.LoginRequestDTO;
import com.todolist.todolistapi.dtos.LoginResponseDTO;
import com.todolist.todolistapi.dtos.UserRequestDTO;
import com.todolist.todolistapi.entities.User;
import com.todolist.todolistapi.exceptions.EmailInUseException;
import com.todolist.todolistapi.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new EmailInUseException("O endereço de e-mail informado já está em uso. " +
                    "Por favor, tente um endereço diferente.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDTO.password());

        User user = new User(userRequestDTO.name(), userRequestDTO.email(), encryptedPassword);

        userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }
}
