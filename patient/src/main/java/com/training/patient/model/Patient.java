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

    @Override
    public String toString() {
        return "Patient: {" +
                "Name=" + name +
                ", Phone='" + phone_number + '\'' +
                ", Created At='" + created_at + '\'' +
                "}";
    }

}
