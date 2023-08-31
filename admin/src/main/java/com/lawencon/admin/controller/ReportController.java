package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<ApplicantReportPojo>> getApplicantReport() {
        List<ApplicantReportPojo> response = reportService.getApplicantReport();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tes")
    public ResponseEntity<?> getReport() {
        try {
            final List<ApplicantReportPojo> list = reportService.getApplicantReport();
            byte[] report = jasperUtil.responseToByteArray(list, null, "Report_Applicant_1");
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "application_report" + "." + "pdf")
                .body(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
            .body("error!");
        }
    }
}
