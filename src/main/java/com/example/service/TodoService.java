package com.example.service;

import com.example.domain.Customer;
import com.example.domain.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoService {
  @Autowired
  TodoRepository todoRepository;

  public List<Todo> findAll() {
    return todoRepository.findAllOrderByName();
  }

  public Todo findOne(Integer id) {
    return todoRepository.findOne(id);
  }

  public Todo create(Todo todo) {
    return todoRepository.save(todo);
  }

  public Todo update(Todo todo) {
    return todoRepository.save(todo);
  }

  public void delete(Integer id) {
    todoRepository.delete(id);
  }
}
