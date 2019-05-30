package com.springhibernate.demo.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity @Getter @Setter
public class Course extends DatabaseObject {
    private String courseName;
    private String briefDescription;
    private Integer lengthInMonths;
}
