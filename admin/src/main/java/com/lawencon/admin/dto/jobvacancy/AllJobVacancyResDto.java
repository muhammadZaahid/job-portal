package com.lawencon.admin.dto.jobvacancy;

public class AllJobVacancyResDto {
    private String id;
    private String code;
    private String title;
    private String picName;
    private String companyName;
    private String jobLevelName;
    private String location;
    private String startDate;
    private String endDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getJobLevelName() {
        return jobLevelName;
    }
    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }  
        
}
