package com.lawencon.admin.dto.offer;

public class OfferingResDto {
    private String id;
    private String applicantId;
    private String offerTime;
    private String offerPicName;
    private String offerLastEmailSend;
    private Double offerSalary;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getOfferTime() {
        return offerTime;
    }
    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }
    public String getOfferPicName() {
        return offerPicName;
    }
    public void setOfferPicName(String offerPicName) {
        this.offerPicName = offerPicName;
    }
    public String getOfferLastEmailSend() {
        return offerLastEmailSend;
    }
    public void setOfferLastEmailSend(String offerLastEmailSend) {
        this.offerLastEmailSend = offerLastEmailSend;
    }
    public Double getOfferSalary() {
        return offerSalary;
    }
    public void setOfferSalary(Double offerSalary) {
        this.offerSalary = offerSalary;
    }
    
}
