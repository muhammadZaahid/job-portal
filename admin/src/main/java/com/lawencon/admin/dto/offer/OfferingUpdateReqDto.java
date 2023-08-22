package com.lawencon.admin.dto.offer;

public class OfferingUpdateReqDto {
    private String applicantId;
    private Double offerSalary;

    public Double getOfferSalary() {
        return offerSalary;
    }

    public void setOfferSalary(Double offerSalary) {
        this.offerSalary = offerSalary;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    
}
