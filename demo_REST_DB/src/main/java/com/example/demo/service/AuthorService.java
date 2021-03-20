package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByEmail(String email) {
        Author author = authorRepository.findByEmail(email);
        return author;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
    }

    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        author.setEmail(authorDetails.getEmail());
        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());

        return authorRepository.save(author);
    }

    public ResponseEntity<Object> deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        authorRepository.delete(author);

        return ResponseEntity.ok().build();
    }
}
