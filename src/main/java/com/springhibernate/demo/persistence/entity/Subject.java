package com.springhibernate.demo.persistence.entity;

import com.springhibernate.demo.model.ComplexityLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter @Setter
public class Subject extends DatabaseObject{
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "complexity_level")
    private ComplexityLevel complexity;
}
