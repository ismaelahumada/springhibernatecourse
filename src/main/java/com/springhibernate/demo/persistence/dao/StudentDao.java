package com.springhibernate.demo.persistence.dao;

import com.springhibernate.demo.persistence.Entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component @Slf4j
public class StudentDao implements IStudentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional get(int id) {
        List<Student> student = em.createQuery("select st from Student st where st.id = :id", Student.class).getResultList();
        return Optional.ofNullable(student);
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        List<Student> studentList = em.createQuery("select st from Student st", Student.class).getResultList();
        return studentList;
    }

    @Override
    @Transactional
    public int save(Object o) {
        em.persist(o);
        log.error("persisted object" + o);
        return 1;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
