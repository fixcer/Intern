package com.example.demo;

import java.util.*;

import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReader {

    CSVReaderAuthor csvReaderAuthor = new CSVReaderAuthor();
    Map<String, Author> authors = csvReaderAuthor.processInputAuthor("authors.csv");

    public static final String dir = "/home/toan/Documents/Intern/Java/data/";

    public Author findByEmail(String email) {
        return authors.get(email);
    }

    public Map<String, Document> processInputBook(String fileName) {
        List<Document> records = new ArrayList<Document>();

        try {
            File file = new File(dir + fileName);
            InputStream IS = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));

            // skip the header of the csv
            records = br.lines().skip(1).map(mapToBook).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Document> books = records.parallelStream().collect(Collectors.toMap(Document::getISBN, book -> book));

        return books;
    }

    public Function<String, Document> mapToBook = (line) -> {
        String[] p = line.split(";");// a CSV has comma separated lines
        String[] emails = p[2].split(",");
        Author[] authorBook = new Author[emails.length];

        for (int i = 0; i < emails.length; i++) {
            Author author = findByEmail(emails[i]);
            authorBook[i] = new Author(author.getEmail(), author.getFirstName(), author.getLastName());
        }

        Document book = new Document(p[0], p[1], authorBook, p[3]);

        return book;
    };


    public Map<String, Document> processInputMagazine(String fileName) {
        List<Document> records = new ArrayList<Document>();

        try {
            File file = new File(dir + fileName);
            InputStream IS = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));

            // skip the header of the csv
            records = br.lines().skip(1).map(mapToMagazine).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Document> magazines = records.parallelStream().collect(Collectors.toMap(Document::getISBN, magazine -> magazine));

        return magazines;
    }

    public Function<String, Document> mapToMagazine = (line) -> {

        String[] p = line.split(";");// a CSV has comma separated lines
        String[] emails = p[2].split(",");
        Author[] authorMagazine = new Author[emails.length];

        for (int i = 0; i < emails.length; i++) {
            Author author = findByEmail(emails[i]);
            authorMagazine[i] = new Author(author.getEmail(), author.getFirstName(), author.getLastName());
        }

        Document magazine = new Document(p[0], p[1], authorMagazine, p[3], false);

        return magazine;
    };
}
