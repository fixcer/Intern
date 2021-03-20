package com.example.demo.controller;


import com.example.demo.CSVToDB;
import com.example.demo.model.Book;
import com.example.demo.model.Magazine;
import com.example.demo.service.BookService;
import com.example.demo.service.MagazineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AuthorService authorService;
    private BookService bookService;
    private MagazineService magazineService;

    @GetMapping
    public String sayHello() {
        return "Hello world!!";
    }

    @GetMapping("/insert")
    public String insertToDB() {
        CSVToDB csvToDB = new CSVToDB();
        List<Author> authors = csvToDB.authorBean("authors.csv");
        List<Book> books = csvToDB.bookBean("books.csv");
        List<Magazine> magazines = csvToDB.magazineBean("magazines.csv");

        try {
            for (Author author: authors) {
                authorService.createAuthor(author);
            }

//            for (Book book: books) {
//                bookService.createBook(book);
//            }
//
//            for (Magazine magazine: magazines) {
//                magazineService.createMagazine(magazine);
//            }

            return "Da them du lieu thanh cong";
        } catch (NullPointerException e){
            e.printStackTrace();

            return "Co loi xay ra";
        }
    }
}