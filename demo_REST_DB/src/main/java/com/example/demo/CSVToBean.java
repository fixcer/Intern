package com.example.demo;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVToBean {

    public static void authorBean(String file) {
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
            csvReader = new CSVReader(new FileReader(file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Author> authors = csvToBean.parse(strategy, csvReader);

        for (Author author: authors) {
            System.out.println(author);
        }
    }

    public static void bookBean(String file) {
        // Hashmap to map CSV data to Bean attributes.
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("title", "Title");
        mapping.put("isbn", "ISBN");
        mapping.put("authors", "Authors");
        mapping.put("description", "Description");

        // HeaderColumnNameTranslateMappingStrategy for Book class
        HeaderColumnNameTranslateMappingStrategy<Book> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Book>();
        strategy.setType(Book.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Book> books = csvToBean.parse(strategy, csvReader);

        for (Book book: books) {
            System.out.println(book);
        }
    }

    public static void magazineBean(String file) {
        // Hashmap to map CSV data to Bean attributes.
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("title", "Title");
        mapping.put("isbn", "ISBN");
        mapping.put("authors", "Authors");
        mapping.put("publicDate", "PublicDate");

        // HeaderColumnNameTranslateMappingStrategy for Book class
        HeaderColumnNameTranslateMappingStrategy<Magazine> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Magazine>();
        strategy.setType(Magazine.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(file), ';');
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        List<Magazine> magazines = csvToBean.parse(strategy, csvReader);

        for (Magazine magazine: magazines) {
            System.out.println(magazine);
        }
    }

}