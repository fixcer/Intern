package com.example.demo;

public class Document {
    public String title;
    public String ISBN;
    public Author[] authors;
    public String publicDate;
    public String description;
    public boolean isBook;

    public Document(String title, String ISBN, Author[] authors, String description) {
        this.title = title;
        this.ISBN = ISBN;
        this.authors = authors;
        this.description = description;
    }

    public Document(String title, String ISBN, Author[] authors, String publicDate, boolean isBook) {
        this.title = title;
        this.ISBN = ISBN;
        this.authors = authors;
        this.publicDate = publicDate;
        this.isBook = isBook;
    }

    public Document(String title, String ISBN) {
        this.title = title;
        this.ISBN = ISBN;
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

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBook() {
        return isBook;
    }

    public void setBook(boolean book) {
        isBook = book;
    }
}
