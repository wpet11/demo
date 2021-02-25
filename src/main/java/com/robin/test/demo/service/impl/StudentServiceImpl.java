package com.robin.test.demo.service.impl;

import com.robin.test.demo.dao.StudentMapper;
import com.robin.test.demo.entiry.Student;
import com.robin.test.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student st) {
        studentMapper.addStudent(st);
    }

    @Override
    public Student queryById(int i) {
        Student st = studentMapper.queryById(i);
        return st;
    }
}
