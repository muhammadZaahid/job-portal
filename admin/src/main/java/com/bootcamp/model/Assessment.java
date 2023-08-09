package com.bootcamp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;


@Entity
@Table(name="t_assessment")
public class Assessment extends BaseEntity{
	
	@Column(name="assessment_venue", nullable = false)
	private String assessmentVenue;
	
	@Column(name="assessment_map", nullable = false)
	private String assessmentMap;
	
	@Column(name="assessment_time", nullable = false)
	private LocalDateTime assessmentTime;
	
	@Column(name="assessment_pic", nullable = false)
	private String assessmentPic;
	
	@Column(name="assessment_pic_phone", nullable = false)
	private String assessmentPicPhone;
	
	@Column(name="assessment_pic_email", nullable = false)
	private String assessmentPicEmail;
	
	@Column(name="assessment_note", nullable = false)
	private String assessmentNote;
	
	@Column(name="assessment_last_email_send", nullable = false)
	private String assessmentLastEmailSend;
		
	@Column(name="assessment_same_interview", nullable = false)
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

	public String getAssessmentPic() {
		return assessmentPic;
	}

	public void setAssessmentPic(String assessmentPic) {
		this.assessmentPic = assessmentPic;
	}

	public String getAssessmentPicPhone() {
		return assessmentPicPhone;
	}

	public void setAssessmentPicPhone(String assessmentPicPhone) {
		this.assessmentPicPhone = assessmentPicPhone;
	}

	public String getAssessmentPicEmail() {
		return assessmentPicEmail;
	}

	public void setAssessmentPicEmail(String assessmentPicEmail) {
		this.assessmentPicEmail = assessmentPicEmail;
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
	
	
}
