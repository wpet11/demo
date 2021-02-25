package com.robin.test.demo.dao;

import com.robin.test.demo.entiry.Student;


//@Mapper
public interface StudentMapper {
    void addStudent(Student st);

    Student queryById(int id);
}
