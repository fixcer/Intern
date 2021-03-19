package com.example.demo;

import java.util.*;

import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReaderAuthor {

    public static final String dir = "/home/toan/Documents/Intern/Java/data/";

    public Map<String, Author> processInputAuthor(String fileName) {
        List<Author> records = new ArrayList<Author>();

        try {
            File file = new File(dir + fileName);
            InputStream IS = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));

            // skip the header of the csv
            records = br.lines().skip(1).map(mapToAuthor).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Author> authors = records.parallelStream().collect(Collectors.toMap(Author::getEmail, author -> author));

        return authors;
    }

    public Function<String, Author> mapToAuthor = (line) -> {

        String[] p = line.split(";");// a CSV has comma separated lines

        Author author = new Author(p[0], p[1], p[2]);

        return author;
    };
}
