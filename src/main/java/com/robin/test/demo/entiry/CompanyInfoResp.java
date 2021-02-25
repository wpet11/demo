package com.robin.test.demo.entiry;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyInfoResp {

    @Excel(name = "公司ID",width = 20)
    private String csfId;
    @Excel(name = "公司名字",width = 20)
    private String name;
    @Excel(name = "证券信息",width = 20)
    private String secu;
    @Excel(name = "证券编码",width = 20)
    private String tick;
    @Excel(name = "证券上市状态",width = 20)
    private Integer listStatus;
}
