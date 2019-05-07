package com.springhibernate.demo.web.controller;

import com.springhibernate.demo.persistence.Entity.Student;
import com.springhibernate.demo.persistence.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentListController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String listStudents(ModelMap model) {
        List<Student> studentList;
        studentList = studentService.listStudents();
        model.put("studentList", studentList);
        return "students";
    }
}
