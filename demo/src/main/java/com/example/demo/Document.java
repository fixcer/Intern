package com.example.demo;

public abstract class Document {
    public String title;
    public String ISBN;
    public Author[] authors;

    public Document(String title, String ISBN, Author[] authors) {
        this.title = title;
        this.ISBN = ISBN;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }
}
