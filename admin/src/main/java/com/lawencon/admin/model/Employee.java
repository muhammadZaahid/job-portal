package com.lawencon.admin.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_employee",uniqueConstraints = {@UniqueConstraint(columnNames = {"candidate_id","company_id"})})
public class Employee extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="candidate_id", nullable = false)
	private Candidate candidate;

	@OneToOne
	@JoinColumn(name="company_id", nullable = false)
	private Company company;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}


