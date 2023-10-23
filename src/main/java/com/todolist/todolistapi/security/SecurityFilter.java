package com.todolist.todolistapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.todolistapi.dtos.ErrorDTO;
import com.todolist.todolistapi.repositories.UserRepository;
import com.todolist.todolistapi.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = this.recoverToken(request);

        if (token != null) {
            String subject = tokenService.validateToken(token);
            Optional<UserDetails> user = userRepository.findByEmail(subject);

            if (user.isPresent()) {
                var authetication = new UsernamePasswordAuthenticationToken(user.get(), null,
                        user.get().getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authetication);
            } else {
                ErrorDTO errorDTO = new ErrorDTO("Usuário não autenticado ou token expirado.",
                        HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name(), LocalDateTime.now().toString());

                ObjectMapper mapper = new ObjectMapper();

                response.setStatus(errorDTO.status());
                response.setContentType("application/json");
                response.getWriter().print(mapper.writeValueAsString(errorDTO));
                response.getWriter().flush();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
