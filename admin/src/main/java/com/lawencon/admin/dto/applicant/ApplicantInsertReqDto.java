package com.lawencon.admin.dto.applicant;

public class ApplicantInsertReqDto {
	
	private String candidateId;
	private String jobVacancyId;
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getJobVacancyId() {
		return jobVacancyId;
	}
	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}

	

}
