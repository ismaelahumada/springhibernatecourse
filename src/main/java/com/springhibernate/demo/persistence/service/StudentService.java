package com.springhibernate.demo.persistence.service;

import com.springhibernate.demo.persistence.Entity.Student;
import com.springhibernate.demo.persistence.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    @Transactional
    public void createStudent(Student student) {
        studentDao.save(student);
    }

    @Transactional
    public List<Student> listStudents() {
        return studentDao.getAll();
    }
}