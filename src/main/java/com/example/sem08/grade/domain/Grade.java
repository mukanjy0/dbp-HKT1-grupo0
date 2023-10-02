package com.example.sem08.grade.domain;

import com.example.sem08.alumno.domain.Alumno;
import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long score;
    @ManyToOne
    Alumno alumno;

    public Grade() {}
    public Grade(Long id, Long score, Alumno alumno) {
        this.id = id;
        this.score = score;
        this.alumno = alumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
