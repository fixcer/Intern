package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookService.findById(id);
    }

    @PutMapping("/book/{id}")
    public Book updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Book bookDetails) {
        Book book = bookService.updateBook(id, bookDetails);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {
        return bookService.deleteBook(id);
    }
}
