package com.springhibernate.demo.persistence.service;

import com.springhibernate.demo.persistence.dao.IStudentDao;
import com.springhibernate.demo.persistence.entity.Student;
import com.springhibernate.demo.util.OnlyAcademicPurposes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service @Slf4j
public class StudentService {
    @Autowired
    private IStudentDao<Student> studentDao;
    @Autowired
    private OnlyAcademicPurposes oap;

    @Transactional
    public void createStudent(Student student) {
        studentDao.save(student);
    }

    @Transactional
    public Collection<Student> listStudents() {
        log.info(oap.printSpaces());
        System.out.println("Entering non tweaked dao");
        Collection<Student> studentDaoAll = studentDao.getAll();
        log.info(oap.printSpaces());
        log.info(oap.printSpaces());
        System.out.println("Entering tweaked dao");
        Collection<Student> studentsFetchJoin = studentDao.getAllStudentsJoinFetchCourses();

        log.info(oap.printSpaces());
        return studentsFetchJoin;
    }

    @Transactional
    public Optional<Student> listStudent(long id) {
        return studentDao.get(id);
    }

    @Transactional
    public void remove(Student student) {
        studentDao.delete(student);
    }
}
