package com.robin.test.demo.entiry;

import lombok.Data;

@Data
public class Student {
    //姓名
    private String name;
    //年龄
    private Integer age;
    //等级
    private TypeEnum type;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}