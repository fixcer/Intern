package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotBlank
    private String authors;

    @NotBlank
    private String description;

    @Override
    public String toString() {
        return "Book: {" +
                "title=" + title +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", description='" + description + '\'' +
                "}";
    }

}