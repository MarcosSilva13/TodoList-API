package com.todolist.todolistapi.repositories;

import com.todolist.todolistapi.entities.Todo;
import com.todolist.todolistapi.enums.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT obj FROM Todo obj JOIN FETCH obj.user WHERE obj.user.id = :id AND obj.status = :status")
    List<Todo> findByUserIdAndStatusEqual(@Param(value = "id") String id, @Param(value = "status") Status status,
                                          Sort createdAt);
}
