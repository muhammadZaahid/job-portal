package com.lawencon.admin.model;

import com.lawencon.base.BaseEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="t_offer",uniqueConstraints = {@UniqueConstraint(columnNames = {"applicant_id"})})
public class Offer extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	@Column(name="offer_time ", nullable = false)
	private LocalDateTime offerTime;
	
	@OneToOne
	@JoinColumn(name="offer_pic", nullable = false)
	private User offerPic;
	
	@Column(name="offer_last_email_send", nullable = false)
	private LocalDateTime offerLastEmailSend;
	
	@Column(name="offer_basic_salary", nullable = false)
	private Double offerBasicSalary;
	
	@Column(name="Offer_letter", nullable = false)
	private String offerLetter;
	
	public LocalDateTime getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(LocalDateTime offerTime) {
		this.offerTime = offerTime;
	}

	public User getOfferPic() {
		return offerPic;
	}

	public void setOfferPic(User offerPic) {
		this.offerPic = offerPic;
	}

	public LocalDateTime getOfferLastEmailSend() {
		return offerLastEmailSend;
	}

	public void setOfferLastEmailSend(LocalDateTime offerLastEmailSend) {
		this.offerLastEmailSend = offerLastEmailSend;
	}

	public Double getOfferBasicSalary() {
		return offerBasicSalary;
	}

	public void setOfferBasicSalary(Double offerBasicSalary) {
		this.offerBasicSalary = offerBasicSalary;
	}

	public String getOfferLetter() {
		return offerLetter;
	}

	public void setOfferLetter(String offerLetter) {
		this.offerLetter = offerLetter;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	
}
