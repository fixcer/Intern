package com.training.patient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "patients")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private int age;

    @Email
    private String email;

    @NotNull
    private String phone_number;

    @NotNull
    private Date created_at;

    @NotNull Date updated_at;

    public Patient() {

    }

    public Patient(Long id, @NotNull String name, @NotNull String gender, @NotNull int age, @Email String email, @NotNull String phone_number, @NotNull Date created_at, @NotNull Date updated_at) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone_number = phone_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
