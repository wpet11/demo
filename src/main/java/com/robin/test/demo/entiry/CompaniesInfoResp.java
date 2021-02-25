package com.robin.test.demo.entiry;

import lombok.Data;

import java.util.List;

@Data
public class CompaniesInfoResp {
    private List<CompanyInfoResp> result;
}
