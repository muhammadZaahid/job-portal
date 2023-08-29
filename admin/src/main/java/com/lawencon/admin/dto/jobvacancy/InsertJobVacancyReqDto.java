package com.lawencon.admin.dto.jobvacancy;

public class InsertJobVacancyReqDto {
    private String title;
    private String picId;
    private String companyId;
    private String jobLevelId;
    private String location;
    private String benefitDesc;
    private String jobDesc;
    private Long salaryFrom;
    private Long salaryTo;
    private Boolean salaryPublish;
    private String startDate;
    private String endDate;
    private String topicId;
    
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
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
    public String getJobDesc() {
        return jobDesc;
    }
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }
    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    
}
