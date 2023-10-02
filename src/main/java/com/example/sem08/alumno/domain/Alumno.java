package com.example.sem08.alumno.domain;

import com.example.sem08.grade.domain.Grade;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    private String code;

    @OneToMany(mappedBy = "alumno")
    List<Grade> grades;

    public Alumno() {}
    public Alumno(Long id, String lastName, String code) {
        this.id = id;
        this.lastName = lastName;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
