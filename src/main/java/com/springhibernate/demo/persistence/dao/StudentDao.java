package com.springhibernate.demo.persistence.dao;

import com.springhibernate.demo.persistence.entity.Address;
import com.springhibernate.demo.persistence.entity.Course;
import com.springhibernate.demo.persistence.entity.Student;
import com.springhibernate.demo.persistence.entity.Subject;
import com.springhibernate.demo.util.OnlyAcademicPurposes;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component @Slf4j
public class StudentDao implements IStudentDao {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private OnlyAcademicPurposes oap;

    @Override
    public Optional<Student> get(long id) {
        Student student = em.createQuery("select st from Student st where id = " + id, Student.class).getSingleResult();
        log.info("---------------Print student from JPQL query: " + student.getEmail() + " ---------------");
        Student hibernateStudent = em.find(Student.class, id);
        log.info("---------------Print student from hibernate find implementation: " + hibernateStudent.getEmail() + " ---------------");
        //return Optional.ofNullable(student1);
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> getAll() {
        log.info("---------------Printing studentList from HQL query---------------");
        List<Student> studentList = em.createQuery("from Student", Student.class).getResultList();
        for (Student s : studentList) {
            log.info(s.getEmail());
            log.info("Print Student's courses if any");
            for (Course c : s.getCourses()) {
                log.info(c.getBriefDescription());
                for (Subject sub : c.getSubjects()){
                    log.info(sub.getName());
                    log.info(sub.getComplexity().name());
                }
            }
        }
        //printAllStudentsWithCriteriaQuery();
        return studentList;
    }

    public List<Student> getAllStudentsJoinFetchCourses() {
        log.info("---------------Printing studentList from HQL query---------------");
        List<Student> studentList = em.createQuery("select distinct s from Student s join fetch s.courses", Student.class).getResultList();
        //modificar la query para usar fetch join de subject
        for (Student s : studentList) {
            log.info(s.getEmail());
            log.info("Print Student's courses");
            for (Course c : s.getCourses()) {
                log.info("Book Description:" + c.getBriefDescription());
                for (Subject sub : c.getSubjects()){
                    log.info(sub.getName());
                    log.info(sub.getComplexity().name());
                }
            }
        }
        return studentList;
    }

    private void printAllStudentsWithCriteriaQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> rootEntry = cq.from(Student.class);
        log.info("---------------Printing all students from Criteria query---------------");
        CriteriaQuery<Student> all = cq.select(rootEntry);
        TypedQuery<Student> allQuery = em.createQuery(all);
        for (Student s : allQuery.getResultList()) {
            log.info(s.getEmail());
        }
    }

    @Override
    @Transactional
    public int save(Object o) {
        Student s = (Student) o;
        Address address = em.find(Address.class, s.getAddress().getId());
        s.setAddress(address);
        ((Session) em.getDelegate()).saveOrUpdate(o);
        log.info("persisted object" + o);
        return 1;
    }

    @Override
    public void delete(Object o) {
        em.remove(o);
    }

}
