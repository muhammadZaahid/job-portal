package com.lawencon.admin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_candidate")
public class Candidate extends BaseEntity{
    @Column(name="nik" )
    private String nik;

    @Column(name="name" )
    private String name;

    @Column(name="email", unique = true )
    private String email;

    @Column(name="phone" )
    private String phone;

    @Column(name="birth_place")
    private String birthPlace;
    
    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="soc_med_1" )
    private String socmed1;

    @Column(name="soc_med_2")
    private String socmed2;

    @Column(name="soc_med_3")
    private String socmed3;

    @Column(name="experience_year")
    private Double experienceYear;

    @Column(name="salary_expect")
    private Double salaryExpectation;
    
    @OneToOne
    @JoinColumn(name="photo_id")
    private File photoId;

    @OneToOne
    @JoinColumn(name="resume_id")
    private File resumeId;
}
