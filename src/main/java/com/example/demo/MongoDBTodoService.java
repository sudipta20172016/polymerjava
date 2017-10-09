package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
final class MongoDBTodoService implements TodoService {

    private final TodoRepository repository;

    @Autowired
    MongoDBTodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public TodoDTO create(TodoDTO todo) {
        Todo persisted = Todo.getBuilder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .build();
        persisted = repository.save(persisted);
        return convertToDTO(persisted);
    }

    @Override
    public TodoDTO delete(String id) throws TodoNotFoundException {
        Todo deleted = findTodoById(id);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }

    @Override
    public List<TodoDTO> findAll() {
        List<Todo> todoEntries = repository.findAll();
        return convertToDTOs(todoEntries);
    }

    private List<TodoDTO> convertToDTOs(List<Todo> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public TodoDTO findById(String id) throws TodoNotFoundException {
        Todo found = findTodoById(id);
        return convertToDTO(found);
    }

    @Override
    public TodoDTO update(TodoDTO todo) throws TodoNotFoundException {
        Todo updated = findTodoById(todo.getId());
        updated.update(todo.getTitle(), todo.getDescription());
        updated = repository.save(updated);
        return convertToDTO(updated);
    }

    private Todo findTodoById(String id) throws TodoNotFoundException {
        Optional<Todo> result = repository.findOne(id);


            return result.orElseThrow(() -> new TodoNotFoundException(id));

    }

    private TodoDTO convertToDTO(Todo model) {
        TodoDTO dto = new TodoDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        return dto;
    }
}