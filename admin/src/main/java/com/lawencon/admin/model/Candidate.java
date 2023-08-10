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
    @Column(name="nik", nullable = false)
    private String nik;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="phone", nullable = false)
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

    @Column(name="experience_year", nullable = false)
    private Double experienceYear;

    @Column(name="salary_expect", nullable = false)
    private Double salaryExpectation;
    
    @OneToOne
    @JoinColumn(name="photo_id")
    private File photo;

    @OneToOne
    @JoinColumn(name="resume_id", nullable = false)
    private File resume;

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSocmed1() {
		return socmed1;
	}

	public void setSocmed1(String socmed1) {
		this.socmed1 = socmed1;
	}

	public String getSocmed2() {
		return socmed2;
	}

	public void setSocmed2(String socmed2) {
		this.socmed2 = socmed2;
	}

	public String getSocmed3() {
		return socmed3;
	}

	public void setSocmed3(String socmed3) {
		this.socmed3 = socmed3;
	}

	public Double getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Double experienceYear) {
		this.experienceYear = experienceYear;
	}

	public Double getSalaryExpectation() {
		return salaryExpectation;
	}

	public void setSalaryExpectation(Double salaryExpectation) {
		this.salaryExpectation = salaryExpectation;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public File getResume() {
		return resume;
	}

	public void setResume(File resume) {
		this.resume = resume;
	}


    
    
}
