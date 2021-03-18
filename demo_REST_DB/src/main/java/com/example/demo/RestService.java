package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class RestService {
    private BookRepository bookRepository;

    @Autowired
    public RestService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity getBookStats(Long id){
        BookEntity bookEntity = bookRepository.findById(id);
        return bookEntity;
    }
}