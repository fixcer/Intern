package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "authors")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    @Override
    public String toString() {
        return "Author: {" +
                "email=" + email +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "}";
    }
}
