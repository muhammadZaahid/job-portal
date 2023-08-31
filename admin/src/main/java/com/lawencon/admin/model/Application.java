package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_application",uniqueConstraints = {@UniqueConstraint(columnNames = {"applicant_id"}) })
public class Application extends BaseEntity{

	@OneToOne
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	
	@Column(name="is_accepted")
	private Boolean isAccepted;
	
	@Column(name="is_rejected")
	private Boolean isRejected;
	

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Boolean getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(Boolean isRejected) {
		this.isRejected = isRejected;
	}
	
	
	
	
}
