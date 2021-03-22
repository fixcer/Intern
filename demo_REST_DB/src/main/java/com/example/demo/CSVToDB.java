package com.example.demo;

import java.io.*;
import java.util.*;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Magazine;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVToDB {

    public static final String dir = "/home/toan/Documents/Intern/Java/data/";

    public List<Author> authorBean(String file) {
        // Hashmap to map CSV data to Bean attributes.
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("email", "Email");
        mapping.put("firstName", "FirstName");
        mapping.put("lastName", "LastName");

        // HeaderColumnNameTranslateMappingStrategy for Book class
        HeaderColumnNameTranslateMappingStrategy<Author> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Author>();
        strategy.setType(Author.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(dir + file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Author> authors = csvToBean.parse(strategy, csvReader);

        return authors;

    }

    public List<Book> bookBean(String file) {
        // Hashmap to map CSV data to Bean attributes.
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("title", "Title");
        mapping.put("isbn", "Isbn");
        mapping.put("authors", "Authors");
        mapping.put("description", "Description");

        // HeaderColumnNameTranslateMappingStrategy for Book class
        HeaderColumnNameTranslateMappingStrategy<Book> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Book>();
        strategy.setType(Book.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(dir + file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Book> books = csvToBean.parse(strategy, csvReader);

        return books;
    }

    public List<Magazine> magazineBean(String file) {
        // Hashmap to map CSV data to Bean attributes.
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("title", "Title");
        mapping.put("isbn", "ISBN");
        mapping.put("author", "Authors");
        mapping.put("publicationDate", "PublicationDate");

        // HeaderColumnNameTranslateMappingStrategy for Book class
        HeaderColumnNameTranslateMappingStrategy<Magazine> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Magazine>();
        strategy.setType(Magazine.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(dir + file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Magazine> magazines = csvToBean.parse(strategy, csvReader);

        return magazines;
    }

}