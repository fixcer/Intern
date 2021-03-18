package com.example.demo;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;


public class Library {
    public static final String dir = "/home/toan/Documents/Intern/Java/data/";

    public static List<String[]> readCSV(String target) throws IOException, CsvException {
        String fileName = dir + target;
        List<String[]> r;

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            r = reader.readAll();
        }

        return r;
    }

    public static Author[] generateAuthor(List<String[]> author) {
        Author[] authors = new Author[author.size()];

        for (int i = 0; i < author.size(); i++) {
            authors[i] = new Author(author.get(i)[0], author.get(i)[1], author.get(i)[2]);
        }

        return authors;
    }

    public static Book[] generateBook(List<String[]> book, Author[] authors) {
        Book[] books = new Book[book.size()];

        for (int i = 0; i < book.size(); i++) {
            String[] emails = book.get(i)[2].split(",");
            Author[] authorBook = new Author[emails.length];

            for (int j = 0; j < emails.length; j++) {
                authorBook[j] = findByEmail(emails[j], authors);
            }

            books[i] = new Book(book.get(i)[0], book.get(i)[1], authorBook, book.get(i)[3]);
        }

        return books;
    }

    public static Magazine[] generateMagazine(List<String[]> magazine, Author[] authors) {
        Magazine[] magazines = new Magazine[magazine.size()];

        for (int i = 0; i < magazine.size(); i++) {
            String[] emails = magazine.get(i)[2].split(",");
            Author[] authorMagazine = new Author[emails.length];

            for (int j = 0; j < emails.length; j++) {
                authorMagazine[j] = findByEmail(emails[j], authors);
            }

            magazines[i] = new Magazine(magazine.get(i)[0], magazine.get(i)[1], authorMagazine, magazine.get(i)[3]);
        }

        return magazines;
    }


    public static Book[] generateBookAndMagazine(Book[] books, Magazine[] magazines) {
        Book[] booksAndMagazines = new Book[books.length + magazines.length];

        int i;
        for (i = 0; i < books.length; i++) {
            booksAndMagazines[i] = new Book(books[i].getTitle(), books[i].getISBN(), books[i].getAuthors(), books[i].getDescription());
        }

        for (int j = 0; j < magazines.length; j++) {
            booksAndMagazines[i++] = new Book(magazines[j].getTitle(), magazines[j].getISBN(), magazines[j].getAuthors(), magazines[j].getPublicDate());
        }

        return booksAndMagazines;
    }

    public static void printInfo(Book documents) {
        System.out.println("---------------");
        System.out.println("Tiêu đề: " + documents.getTitle());
        System.out.println("ISBN: " + documents.getISBN());
        String[] words = documents.getDescription().split(".");
        if (words.length == 3) {
            System.out.println("Ngày xuất bản: " + documents.getDescription());
        } else {
            System.out.println("Mô tả: " + documents.getDescription());
        }
        for (int i = 0; i < documents.getAuthors().length; i++) {
            System.out.println("Email tác giả: " + documents.getAuthors()[i].getEmail());
            System.out.println("Tên tác giả: " + documents.getAuthors()[i].getFirstName() + " " + documents.getAuthors()[i].getLastName());
            System.out.println("***");
        }
        System.out.println("===============");

    }

    public static void printBooksAndMagazines(Book[] documents) {
        System.out.println("Sách và tạp chí có trong hệ thống");
        for (int i = 0; i < documents.length; i++) {
            printInfo(documents[i]);
        }

    }

    public static void findByISBN(Book[] documents) {
        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập ISBN: ");
        String isbn = input.nextLine();

        for (int i = 0; i < documents.length; i++) {
            if (documents[i].getISBN().contains(isbn)) {
                count++;
                printInfo(documents[i]);
            }
        }

        if (count == 0) {
            System.out.println("Không tìm thấy.");
        }
    }

    public static void findByAuthor(Book[] documents) {
        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập tên tác giả: ");
        String name = input.nextLine();

        for (int i = 0; i < documents.length; i++) {
            for (int j = 0; j < documents[i].getAuthors().length; j++) {
                if (documents[i].getAuthors()[j].getFirstName().contains(name) || documents[i].getAuthors()[j].getLastName().contains(name)) {
                    printInfo(documents[i]);
                }
            }
        }


        if (count == 0) {
            System.out.println("Không tìm thấy.");
        }
    }

    public static Author findByEmail(String email, Author[] authors) {
        for (int i = 0; i < authors.length; i++) {
            if (email.equals(authors[i].getEmail())) {
                return new Author(email, authors[i].getFirstName(), authors[i].getLastName());
            }
        }

        return null;
    }

    public static void orderBooksAndMagazines(Book[] documents) {
        int n = documents.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (documents[i].getTitle().compareTo(documents[j].getTitle()) > 0) {
                    Book tmp = documents[i];
                    documents[i] = documents[j];
                    documents[j] = tmp;
                }
            }
        }

        printBooksAndMagazines(documents);
    }

    public static void main(String[] args) throws IOException, CsvException {
        /**
         * Lấy dữ liệu từ các file CSV
        */
        List<String[]> authorData = readCSV("authors.csv");
        List<String[]> bookData = readCSV("books.csv");
        List<String[]> magazineData = readCSV("magazines.csv");

        Author[] authors = generateAuthor(authorData);
        Book[] books = generateBook(bookData, authors);
        Magazine[] magazines = generateMagazine(magazineData, authors);

        // Tiện cho việc sắp xếp
        Book[] booksAndMagazines = generateBookAndMagazine(books, magazines);


        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Chào mừng bạn đến với thư viện");
            System.out.println("============================");
            System.out.println("1. Tất cả các cuốn sách và tạp chí");
            System.out.println("2. Tìm kiếm bằng ISBN");
            System.out.println("3. Tìm kiếm bằng tên tác giả");
            System.out.println("4. Sắp xếp");

            System.out.print("Mời bạn lựa chọn chức năng: ");
            int number = input.nextInt();

            switch (number) {
                case 1:
                    printBooksAndMagazines(booksAndMagazines);
                    break;
                case 2:
                    findByISBN(booksAndMagazines);
                    break;
                case 3:
                    findByAuthor(booksAndMagazines);
                    break;
                case 4:
                    orderBooksAndMagazines(booksAndMagazines);
                    break;
                default:
                    System.out.println("Đầu vào bạn nhập không hợp lệ");
            }
            System.out.print("Bạn có muốn tiếp tục? (y): ");
            String c = input.next();
            if (c.compareTo("y")*c.compareTo("Y") != 0) {
                break;
            }
        }

    }

}
