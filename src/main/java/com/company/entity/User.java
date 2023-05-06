package com.company.entity;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date birthDate;
    private Country nationality;
    private Country birthPlace;
    private List<UserSkill> skills;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String phone, String email, Date birthDate, Country nationality, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
    }
}
