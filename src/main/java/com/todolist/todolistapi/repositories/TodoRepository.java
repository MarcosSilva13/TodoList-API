package com.todolist.todolistapi.repositories;

import com.todolist.todolistapi.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT obj FROM Todo obj JOIN FETCH obj.user WHERE obj.user.id = :id")
    List<Todo> findByUserId(@Param(value = "id") String id);
}
