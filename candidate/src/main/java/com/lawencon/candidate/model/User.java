package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name="t_user")
public class User extends BaseEntity {

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate profile) {
        this.candidate = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
