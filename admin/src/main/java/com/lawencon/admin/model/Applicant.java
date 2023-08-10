package com.lawencon.admin.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name="t_applicant")
public class Applicant extends BaseEntity {

   @ManyToOne
   @JoinColumn(name = "candidate_id", nullable = false)
   private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_vacancy_id", nullable = false)
    private JobVacancy jobVacancy;

    @Column(name = "current_stage", nullable = false)
    private String currentStage;

    @Column(name = "applied_date", nullable = false)
    private LocalDate appliedDate;
    
    @Column(name = "stg_application")
    private boolean stgApplication;

    @Column(name = "stg_assessment")
    private boolean stgAssessment;

    @Column(name = "stg_interview")
    private boolean stgInterview;

    @Column(name = "stg_mcu")
    private boolean stgMcu;

    @Column(name = "stg_offer")
    private boolean stgOffer;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public JobVacancy getJobVacancy() {
		return jobVacancy;
	}

	public void setJobVacancy(JobVacancy jobVacancy) {
		this.jobVacancy = jobVacancy;
	}



	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public boolean isStgApplication() {
		return stgApplication;
	}

	public void setStgApplication(boolean stgApplication) {
		this.stgApplication = stgApplication;
	}

	public boolean isStgAssessment() {
		return stgAssessment;
	}

	public void setStgAssessment(boolean stgAssessment) {
		this.stgAssessment = stgAssessment;
	}

	public boolean isStgInterview() {
		return stgInterview;
	}

	public void setStgInterview(boolean stgInterview) {
		this.stgInterview = stgInterview;
	}

	public boolean isStgMcu() {
		return stgMcu;
	}

	public void setStgMcu(boolean stgMcu) {
		this.stgMcu = stgMcu;
	}

	public boolean isStgOffer() {
		return stgOffer;
	}

	public void setStgOffer(boolean stgOffer) {
		this.stgOffer = stgOffer;
	}

	
    
    
}