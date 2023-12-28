package ru.maksimov.RESTApp.dto;

import jakarta.validation.constraints.NotEmpty;

public class NewBookDTO {
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Author of book cannot be empty")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
