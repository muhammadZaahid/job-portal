package com.lawencon.admin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_blacklist",uniqueConstraints = {@UniqueConstraint(columnNames = {"candidate_id","company_id"})})
public class Blacklist extends BaseEntity{
    
    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "company_id")
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
