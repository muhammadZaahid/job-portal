package com.lawencon.candidate.dto.jobvacancy;

public class JobVacancyDetailResDto {

	private String id;
    private String code;
    private String title;
    private String companyName;
    private String jobLevelName;
    private String location;
    private String startDate;
    private String endDate;
    private String benefitDesc;
	private String jobDesc;
    private Boolean salaryPublish;
    private Double SalaryFrom;
    private Double SalaryTo;
    
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getBenefitDesc() {
		return benefitDesc;
	}
	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
	}
	public Boolean getSalaryPublish() {
		return salaryPublish;
	}
	public void setSalaryPublish(Boolean salaryPublish) {
		this.salaryPublish = salaryPublish;
	}
	public Double getSalaryFrom() {
		return SalaryFrom;
	}
	public void setSalaryFrom(Double salaryFrom) {
		SalaryFrom = salaryFrom;
	}
	public Double getSalaryTo() {
		return SalaryTo;
	}
	public void setSalaryTo(Double salaryTo) {
		SalaryTo = salaryTo;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
    
    
}
