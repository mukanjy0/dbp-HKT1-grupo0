package com.example.sem08.grade.controller;

import com.example.sem08.alumno.domain.Alumno;
import com.example.sem08.alumno.domain.AlumnoRepository;
import com.example.sem08.grade.domain.Grade;
import com.example.sem08.grade.domain.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @GetMapping
    public ResponseEntity<List<Grade>> grades() {
        List<Grade> grades = gradeRepository.findAll();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{x}")
    public ResponseEntity<Grade> grade(@PathVariable Long x) {
        Optional<Grade> grade = gradeRepository.findById(x);
        if (grade.isPresent()) {
            return new ResponseEntity<>(grade.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/alumno/{x}")
    public ResponseEntity<List<Grade>> gradesByAlumno(@PathVariable Long x) {
        Optional<Alumno> alumno = alumnoRepository.findById(x);
        if (!alumno.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Grade> grades = gradeRepository.findAllByAlumno(alumno.get());
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> grade(@RequestBody Grade grade) {
        Optional<Alumno> alumno = alumnoRepository.findById(grade.getAlumno().getId());
        if (!alumno.isPresent()) {
            return ResponseEntity.status(401).body("Alumno no existe");
        }

        grade.setAlumno(alumno.get());
        gradeRepository.save(grade);

        return ResponseEntity.status(201).body("Created");
    }
}
