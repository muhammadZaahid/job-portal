package com.bootcamp.model;

import javax.persistence.*;

import com.bootcamp.base.BaseEntity;

@Entity
@Table(name="t_employee")
public class Employee extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="candidate_id", nullable = false)
	private Candidate candidate;
}
