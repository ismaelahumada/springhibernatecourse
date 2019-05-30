package com.springhibernate.demo.persistence.dao;

import java.util.Collection;

public interface IStudentDao<T> extends Dao {
    Collection<T> getAllStudentsFetchJoinCourses();
}
