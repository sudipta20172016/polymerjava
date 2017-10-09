package com.example.demo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public final class TodoDTO {

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String id;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Size(max = Todo.MAX_LENGTH_DESCRIPTION)
    private String description;

    @NotEmpty
    @Size(max = Todo.MAX_LENGTH_TITLE)
    private String title;

    //Constructor, getters, and setters are omitted
}
