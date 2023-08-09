package com.bootcamp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_hired")
public class Hired extends BaseEntity{
	
	@Column(name="hired_venue", nullable = false)
	private String hiredVenue;
	
	@Column(name="hired_map", nullable= false)
	private String hiredMap;
	
	@Column(name="hired_time", nullable = false)
	private String hiredTime;
	
	@Column(name="hired_pic", nullable =false)
	private String hiredPic;
	
	@Column(name="hired_pic_phone", nullable = false)
	private String hiredPicPhone;
	
	@Column(name="hired_pic_email", nullable=false)
	private String hiredPicEmail;
	
	@Column(name="hired_note", nullable = false)
	private String hiredNote;
	
	@Column(name="hired_last_email_send", nullable = false)
	private LocalDateTime hiredLastEmailSend;
	
	@Column(name="hired_eff_begin", nullable = false)
	private LocalDate hiredEffBegin;
	
	@Column(name="hired_eff_end", nullable = false)
	private LocalDate hiredEffEnd;

	public String getHiredVenue() {
		return hiredVenue;
	}

	public void setHiredVenue(String hiredVenue) {
		this.hiredVenue = hiredVenue;
	}

	public String getHiredMap() {
		return hiredMap;
	}

	public void setHiredMap(String hiredMap) {
		this.hiredMap = hiredMap;
	}

	public String getHiredTime() {
		return hiredTime;
	}

	public void setHiredTime(String hiredTime) {
		this.hiredTime = hiredTime;
	}

	public String getHiredPic() {
		return hiredPic;
	}

	public void setHiredPic(String hiredPic) {
		this.hiredPic = hiredPic;
	}

	public String getHiredPicPhone() {
		return hiredPicPhone;
	}

	public void setHiredPicPhone(String hiredPicPhone) {
		this.hiredPicPhone = hiredPicPhone;
	}

	public String getHiredPicEmail() {
		return hiredPicEmail;
	}

	public void setHiredPicEmail(String hiredPicEmail) {
		this.hiredPicEmail = hiredPicEmail;
	}

	public String getHiredNote() {
		return hiredNote;
	}

	public void setHiredNote(String hiredNote) {
		this.hiredNote = hiredNote;
	}

	public LocalDateTime getHiredLastEmailSend() {
		return hiredLastEmailSend;
	}

	public void setHiredLastEmailSend(LocalDateTime hiredLastEmailSend) {
		this.hiredLastEmailSend = hiredLastEmailSend;
	}

	public LocalDate getHiredEffBegin() {
		return hiredEffBegin;
	}

	public void setHiredEffBegin(LocalDate hiredEffBegin) {
		this.hiredEffBegin = hiredEffBegin;
	}

	public LocalDate getHiredEffEnd() {
		return hiredEffEnd;
	}

	public void setHiredEffEnd(LocalDate hiredEffEnd) {
		this.hiredEffEnd = hiredEffEnd;
	}
	
	

}
