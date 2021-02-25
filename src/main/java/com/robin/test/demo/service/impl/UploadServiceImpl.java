package com.robin.test.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.robin.test.demo.entiry.CompaniesESResp;
import com.robin.test.demo.entiry.Company;
import com.robin.test.demo.entiry.CompanyESResp;
import com.robin.test.demo.entiry.CompanyInfoResp;
import com.robin.test.demo.service.CompanyInfoService;
import com.robin.test.demo.service.UploadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private CompanyInfoService companyInfoService;

    @Override
    public String upload(InputStream inputStream) {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        try {
            List<Company> list = ExcelImportUtil.importExcel(inputStream, Company.class, params);
            list.stream().forEach(x->System.out.println(x.getName()));
            List<String> nameList = list.stream().map(Company::getName).collect(Collectors.toList());

            List<CompanyInfoResp> info =nameList.stream().map(x->{
                CompaniesESResp companyInfo = companyInfoService.getCompanyInfo(x, 0, 20);
                CompanyESResp company = companyInfo.getResult().get(0);
                return convertToCompanyInfoResp(company);
            }).collect(Collectors.toList());

            ExportParams exportParams = new ExportParams("公司信息","导出结果");
            Workbook sheets = ExcelExportUtil.exportExcel(exportParams, CompanyInfoResp.class, info);;
            OutputStream n= new FileOutputStream("C:\\Users\\robin.wang_ext\\Desktop\\公司.xls");
            sheets.write(n);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    private CompanyInfoResp convertToCompanyInfoResp(CompanyESResp resp){
        return new CompanyInfoResp(
                resp.getCsfId(),
                resp.getName(),
                CollectionUtils.isEmpty(resp.getSecurities()) ? null:resp.getSecurities().get(0).getSecu(),
                CollectionUtils.isEmpty(resp.getSecurities()) ? null:resp.getSecurities().get(0).getTick(),
                null);
    }
}
