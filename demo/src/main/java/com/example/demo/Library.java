package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Library {

    public static void printInfo(Map.Entry<String, Document> document) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Tiêu đề: " + document.getValue().getTitle());
        System.out.println("ISBN: " + document.getKey());
        if (document.getValue().getDescription() == null) {
            System.out.println("Ngày xuất bản: " + document.getValue().getPublicDate());
        } else {
            System.out.println("Mô tả: " + document.getValue().getDescription());
        }
        System.out.println("Tác giả:");
        Arrays.stream(document.getValue().getAuthors()).forEach(author -> System.out.println("\tEmail: " + author.getEmail() + "\n\tName: " + author.getFirstName() + " " + author.getLastName()));
    }

    public static void findByISBN(Map<String, Document> documents) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập ISBN: ");
        String isbn = input.nextLine();

        System.out.println("Kết quả tìm kiếm");
        documents.entrySet().stream().filter(doc -> doc.getKey().contains(isbn)).forEach(Library::printInfo);
        System.out.println("******************************************");
    }

    public static boolean findByName(String name, Author[] authors) {
        List<Author> au = Arrays.stream(authors).filter(a -> a.getFirstName().toLowerCase(Locale.ROOT).contains(name) || a.getLastName().toLowerCase(Locale.ROOT).contains(name)).collect(Collectors.toList());

        return au.size() > 0;
    }

    public static void findByAuthor(Map<String, Document> documents) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập tên tác giả: ");
        String name = input.nextLine();

        System.out.println("Kết quả tìm kiếm");
        documents.entrySet().stream().filter(d -> findByName(name, d.getValue().getAuthors())).forEach(Library::printInfo);
        System.out.println("******************************************");
    }

    public static void main(String[] args) {

        CSVReader csvReader = new CSVReader();
        Map<String, Document> books = csvReader.processInputBook("books.csv");
        Map<String, Document> magazines = csvReader.processInputMagazine("magazines.csv");

        Map<String, Document> booksAndMagazines = Stream.concat(books.entrySet().stream(), magazines.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (value1, value2) -> new Document(value2.getISBN(), value1.getTitle())));

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
                    System.out.println("Sách và tạp chí có trong hệ thống");
                    booksAndMagazines.entrySet().stream().forEach(Library::printInfo);
                    break;
                case 2:
                    findByISBN(booksAndMagazines);
                    break;
                case 3:
                    findByAuthor(booksAndMagazines);
                    break;
                case 4:
                    System.out.println("Sách và tạp chí có trong hệ thống đã được sắp xếp");
                    booksAndMagazines.entrySet().stream().sorted((b1, b2) -> b1.getValue().getTitle().compareTo(b2.getValue().getTitle())).forEach(Library::printInfo);
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
