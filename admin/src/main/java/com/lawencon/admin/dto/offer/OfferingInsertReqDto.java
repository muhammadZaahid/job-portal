package com.lawencon.admin.dto.offer;

public class OfferingInsertReqDto {
    private String applicantId;
    private Long offerSalary;
    
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public Long getOfferSalary() {
        return offerSalary;
    }
    public void setOfferSalary(Long offerSalary) {
        this.offerSalary = offerSalary;
    }    
}
