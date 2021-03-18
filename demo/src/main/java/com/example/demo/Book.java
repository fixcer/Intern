package com.example.demo;

public class Book extends Document {
    public String description;

    public Book(String title, String ISBN, Author[] authors, String description) {
        super(title, ISBN, authors);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
