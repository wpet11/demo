package com.robin.test.demo.entiry;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class TeacherEntity {

    @Excel(name = "教师姓名",height = 20,width = 30,isImportField = "true_st")
    private String name;
    @Excel(name = "教师性别",replace = {"男_1","女_2"},suffix = "生",isImportField = "true_st")
    private int sex;
}
