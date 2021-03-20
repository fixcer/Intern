package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/author")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable(value = "id") Long id) {
        return authorService.findById(id);
    }

    @PutMapping("/author/{id}")
    public Author updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Author authorDetails) {
        Author author = authorService.updateAuthor(id, authorDetails);
        return author;
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Long id) {
        return authorService.deleteAuthor(id);
    }
}
