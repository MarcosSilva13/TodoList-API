package com.todolist.todolistapi.repositories;

import com.todolist.todolistapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserDetails> findByEmail(String email);
}
