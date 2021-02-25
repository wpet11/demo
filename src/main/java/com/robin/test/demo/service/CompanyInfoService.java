package com.robin.test.demo.service;


import com.robin.test.demo.entiry.CompaniesESResp;

public interface CompanyInfoService {
    CompaniesESResp getCompanyInfo(String keyword, Integer page, Integer size);
}
