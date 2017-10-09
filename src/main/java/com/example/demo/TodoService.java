package com.example.demo;

import java.util.List;

interface TodoService {

    TodoDTO create(TodoDTO todo);

    TodoDTO delete(String id) throws TodoNotFoundException;

    List<TodoDTO> findAll();

    TodoDTO findById(String id) throws TodoNotFoundException;

    TodoDTO update(TodoDTO todo) throws TodoNotFoundException;
}
