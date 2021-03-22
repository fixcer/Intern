package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "magazines")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotBlank
    private String publicationDate;

    @NotBlank
    private String authors;

    @Override
    public String toString() {
        return "Magazine: {" +
                "title=" + title +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", publication_date='" + publicationDate + '\'' +
                "}";
    }


}