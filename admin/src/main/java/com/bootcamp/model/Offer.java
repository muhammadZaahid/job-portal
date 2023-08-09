package com.bootcamp.model;

import com.lawencon.base.BaseEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_offer")
public class Offer extends BaseEntity {
	
	@Column(name="offer_venue ", nullable = false)
	private String offerVenue;
	
	@Column(name="offer_map ", nullable = false)
	private String offerMap;
	
	@Column(name="offer_time ", nullable = false)
	private LocalDateTime offerTime;
	
	@Column(name="offer_pic", nullable = false)
	private String offerPic;
	
	@Column(name="offer_pic_mobile", nullable = false)
	private String offerPicMobile;
	
	@Column(name="offer_pic_email", nullable = false)
	private String offerPicEmail;
	
	@Column(name="offer_note", nullable = false)
	private String offerNote;
	
	@Column(name="offer_last_email_send", nullable = false)
	private LocalDateTime offerLastEmailSend;
	
	@Column(name="offer_basic_salary", nullable = false)
	private Long offerBasicSalary;
	
	@Column(name="offer_grade", nullable = false)
	private String offerGrade;
	
	@Column(name="Offer_letter", nullable = false)
	private String offerLetter;

	public String getOfferVenue() {
		return offerVenue;
	}

	public void setOfferVenue(String offerVenue) {
		this.offerVenue = offerVenue;
	}

	public String getOfferMap() {
		return offerMap;
	}

	public void setOfferMap(String offerMap) {
		this.offerMap = offerMap;
	}

	public LocalDateTime getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(LocalDateTime offerTime) {
		this.offerTime = offerTime;
	}

	public String getOfferPic() {
		return offerPic;
	}

	public void setOfferPic(String offerPic) {
		this.offerPic = offerPic;
	}

	public String getOfferPicMobile() {
		return offerPicMobile;
	}

	public void setOfferPicMobile(String offerPicMobile) {
		this.offerPicMobile = offerPicMobile;
	}

	public String getOfferPicEmail() {
		return offerPicEmail;
	}

	public void setOfferPicEmail(String offerPicEmail) {
		this.offerPicEmail = offerPicEmail;
	}

	public String getOfferNote() {
		return offerNote;
	}

	public void setOfferNote(String offerNote) {
		this.offerNote = offerNote;
	}

	public LocalDateTime getOfferLastEmailSend() {
		return offerLastEmailSend;
	}

	public void setOfferLastEmailSend(LocalDateTime offerLastEmailSend) {
		this.offerLastEmailSend = offerLastEmailSend;
	}

	public Long getOfferBasicSalary() {
		return offerBasicSalary;
	}

	public void setOfferBasicSalary(Long offerBasicSalary) {
		this.offerBasicSalary = offerBasicSalary;
	}

	public String getOfferGrade() {
		return offerGrade;
	}

	public void setOfferGrade(String offerGrade) {
		this.offerGrade = offerGrade;
	}

	public String getOfferLetter() {
		return offerLetter;
	}

	public void setOfferLetter(String offerLetter) {
		this.offerLetter = offerLetter;
	}
	
	
}
