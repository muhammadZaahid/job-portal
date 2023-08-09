package com.bootcamp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_interview")
public class Interview extends BaseEntity{
	
	@Column(name="interview_venue ", nullable = false)
	private String interviewVenue;
	
	@Column(name="interview_map ", nullable = false)
	private String interviewMap;
	
	@Column(name="interview_time", nullable = false)
	private LocalDateTime interviewTime;
	
	@Column(name="interview_pic", nullable = false)
	private String interviewPic;
	
	@Column(name="interview_pic_phone", nullable = false)
	private String interviewPicPhone;
	
	@Column(name="interview_pic_email", nullable = false)
	private String interviewPicEmail;
	
	@Column(name="interview_note", nullable = false)
	private String interviewNote;
	
	@Column(name="interview_last_email_send", nullable = false)
	private String interviewLastEmailSend;

	public String getInterviewVenue() {
		return interviewVenue;
	}

	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
	}

	public String getInterviewMap() {
		return interviewMap;
	}

	public void setInterviewMap(String interviewMap) {
		this.interviewMap = interviewMap;
	}

	public LocalDateTime getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(LocalDateTime interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewPic() {
		return interviewPic;
	}

	public void setInterviewPic(String interviewPic) {
		this.interviewPic = interviewPic;
	}

	public String getInterviewPicPhone() {
		return interviewPicPhone;
	}

	public void setInterviewPicPhone(String interviewPicPhone) {
		this.interviewPicPhone = interviewPicPhone;
	}

	public String getInterviewPicEmail() {
		return interviewPicEmail;
	}

	public void setInterviewPicEmail(String interviewPicEmail) {
		this.interviewPicEmail = interviewPicEmail;
	}

	public String getInterviewNote() {
		return interviewNote;
	}

	public void setInterviewNote(String interviewNote) {
		this.interviewNote = interviewNote;
	}

	public String getInterviewLastEmailSend() {
		return interviewLastEmailSend;
	}

	public void setInterviewLastEmailSend(String interviewLastEmailSend) {
		this.interviewLastEmailSend = interviewLastEmailSend;
	}
		

	
}
