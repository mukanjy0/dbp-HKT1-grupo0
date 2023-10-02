package com.example.sem08.alumno.controller;

import com.example.sem08.alumno.domain.Alumno;
import com.example.sem08.alumno.domain.AlumnoRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @GetMapping
    public ResponseEntity<List<Alumno>> alumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{x}")
    public ResponseEntity<Alumno> alumno(@PathVariable Long x) {
        Optional<Alumno> alumno = alumnoRepository.findById(x);
        if (alumno.isPresent()) {
            return new ResponseEntity<>(alumno.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PostMapping
    public ResponseEntity<String> alumno(@RequestBody Alumno alumno) {
        alumnoRepository.save(alumno);
        return ResponseEntity.status(201).body("Created");
    }
}
