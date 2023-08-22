package com.lawencon.admin.dto.jobvacancy;

public class JobVacancyResDto {
    private String id;
    private String title;
    private String picId;
    private String jobLevelId;
    private String location;
    private String benefitDesc;
    private Long salaryFrom;
    private Long salaryTo;
    private Boolean salaryPublish;
    private String startDate;
    private String endDate;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPicId() {
        return picId;
    }
    public void setPicId(String picId) {
        this.picId = picId;
    }
    public String getJobLevelId() {
        return jobLevelId;
    }
    public void setJobLevelId(String jobLevelId) {
        this.jobLevelId = jobLevelId;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getBenefitDesc() {
        return benefitDesc;
    }
    public void setBenefitDesc(String benefitDesc) {
        this.benefitDesc = benefitDesc;
    }
    public Long getSalaryFrom() {
        return salaryFrom;
    }
    public void setSalaryFrom(Long salaryFrom) {
        this.salaryFrom = salaryFrom;
    }
    public Long getSalaryTo() {
        return salaryTo;
    }
    public void setSalaryTo(Long salaryTo) {
        this.salaryTo = salaryTo;
    }
    public Boolean getSalaryPublish() {
        return salaryPublish;
    }
    public void setSalaryPublish(Boolean salaryPublish) {
        this.salaryPublish = salaryPublish;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    } 
}
