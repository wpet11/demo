package com.robin.test.demo.entiry;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class Company {

    @Excel(name = "公司名")
    private String name;
}
