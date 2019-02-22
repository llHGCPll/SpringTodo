package com.example.repository;

import com.example.domain.Customer;
import com.example.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
  @Query("SELECT x FROM Todo x ORDER BY x.id")
  List<Todo> findAllOrderByName();

  @Query("SELECT x FROM Todo x ORDER BY x.id")
  Page<Todo> findAllOrderByName(Pageable pageable);
}
