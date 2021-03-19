package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {

    @Id
    private Long id;

    public String email;
    public String firstName;
    public String lastName;

    @Override
    public String toString() {
        return "Author [email = " + email + ", fullName = " + firstName + " " + lastName + "]";
    }

}