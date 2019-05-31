package com.springhibernate.demo.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity @Getter @Setter
public class Course extends DatabaseObject {
    private String courseName;
    private String briefDescription;
    private Integer lengthInMonths;
    @OneToMany
    List<Subject> subjects;
}

/*
    Crear una relacion oneToMany de Course a Subject teniendo subject los atributos Integer(1-10) complexity y String name
    Recorrer e imprimir subjects en getAll
    Modificar getAllStudentsJoinFetchCourses para que realice un fetch join de subjects. recorrer subjects e imprimir subject.name
 */