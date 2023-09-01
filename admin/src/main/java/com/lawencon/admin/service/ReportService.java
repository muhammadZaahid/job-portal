package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ReportDao;
import com.lawencon.admin.pojo.report.ApplicantReportPojo;

@Service
public class ReportService {
    @Autowired
    ReportDao reportDao;

    public List<ApplicantReportPojo> getApplicantReport(String startDate, String endDate){
        return reportDao.getForApplicant(startDate,endDate);
    }
}
