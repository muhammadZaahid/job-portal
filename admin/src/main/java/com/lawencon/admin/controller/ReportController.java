package com.lawencon.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.pojo.report.ApplicantReportPojo;
import com.lawencon.admin.service.ReportService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    JasperUtil jasperUtil;

    @GetMapping("/applicant")
    public ResponseEntity<List<ApplicantReportPojo>> getApplicantReport(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        List<ApplicantReportPojo> response = reportService.getApplicantReport(startDate, endDate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> getReport(@RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate) {
        try {
            final List<ApplicantReportPojo> list = reportService.getApplicantReport(startDate, endDate);
            Map<String, Object> params = new HashMap<>();
            Integer sumDays = 0;
            double averageDays = Double.valueOf(0);
            for (int i = 0; i < list.size(); i++) {
                sumDays += list.get(i).getDaysNumber();
            }
            averageDays = sumDays / list.size();
            params.put("averageDays", averageDays+" days");
            byte[] report = jasperUtil.responseToByteArray(list, params, "Report_Applicant_2");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + "application_report" + "." + "pdf")
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("error!");
        }
    }
}
