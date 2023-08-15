package com.lawencon.candidate.dto.applicant;

public class ApplicantInsertAdminReqDto {
    private String candidateCode;
	private String jobVacancyCode;
	private String applicantCode;
	
	public ApplicantInsertAdminReqDto(String candidateCode, String jobVacancyCode,String applicantCode) {
		this.candidateCode = candidateCode;
		this.jobVacancyCode = jobVacancyCode;
		this.applicantCode = applicantCode;
	}
	
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

	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
}
