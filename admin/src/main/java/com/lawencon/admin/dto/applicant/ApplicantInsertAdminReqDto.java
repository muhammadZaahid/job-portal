package com.lawencon.admin.dto.applicant;

public class ApplicantInsertAdminReqDto {
    private String candidateCode;
	private String jobVacancyCode;
	
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getJobVacancyCode() {
		return jobVacancyCode;
	}
	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}
}
