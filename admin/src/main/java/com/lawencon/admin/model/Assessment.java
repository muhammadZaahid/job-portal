package com.lawencon.admin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;


@Entity
@Table(name="t_assessment")
public class Assessment extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "applicant_id",nullable = false)
	private Applicant applicantId;

	@Column(name="assessment_venue", nullable = false)
	private String assessmentVenue;
	
	@Column(name="assessment_map", nullable = false)
	private String assessmentMap;
	
	@Column(name="assessment_time", nullable = false)
	private LocalDateTime assessmentTime;
	
	@OneToOne
	@JoinColumn(name="assessment_pic", nullable = false)
	private User assessmentPic;
	
	@Column(name="assessment_score")
	private String assessmentScore;

	@Column(name="assessment_note")
	private String assessmentNote;
	
	@Column(name="assessment_last_email_send", nullable = false)
	private String assessmentLastEmailSend;
		
	@Column(name="assessment_same_interview")
	private String assessmentSameInterview;

	public String getAssessmentVenue() {
		return assessmentVenue;
	}

	public void setAssessmentVenue(String assessmentVenue) {
		this.assessmentVenue = assessmentVenue;
	}

	public String getAssessmentMap() {
		return assessmentMap;
	}

	public void setAssessmentMap(String assessmentMap) {
		this.assessmentMap = assessmentMap;
	}

	public LocalDateTime getAssessmentTime() {
		return assessmentTime;
	}

	public void setAssessmentTime(LocalDateTime assessmentTime) {
		this.assessmentTime = assessmentTime;
	}

	public User getAssessmentPic() {
		return assessmentPic;
	}

	public void setAssessmentPic(User assessmentPic) {
		this.assessmentPic = assessmentPic;
	}

	public String getAssessmentNote() {
		return assessmentNote;
	}

	public void setAssessmentNote(String assessmentNote) {
		this.assessmentNote = assessmentNote;
	}

	public String getAssessmentLastEmailSend() {
		return assessmentLastEmailSend;
	}

	public void setAssessmentLastEmailSend(String assessmentLastEmailSend) {
		this.assessmentLastEmailSend = assessmentLastEmailSend;
	}

	public String getAssessmentSameInterview() {
		return assessmentSameInterview;
	}

	public void setAssessmentSameInterview(String assessmentSameInterview) {
		this.assessmentSameInterview = assessmentSameInterview;
	}

	public Applicant getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Applicant applicantId) {
		this.applicantId = applicantId;
	}

	public String getAssessmentScore() {
		return assessmentScore;
	}

	public void setAssessmentScore(String assessmentScore) {
		this.assessmentScore = assessmentScore;
	}
	
	
}
