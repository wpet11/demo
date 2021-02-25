package com.robin.test.demo.service;

import com.robin.test.demo.entiry.Student;

public interface StudentService {
    void addStudent(Student st);

    Student queryById(int i);
}
