package com.lawencon.admin.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_employee")
public class Employee extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="candidate_id", nullable = false)
	private Candidate candidate;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
}


