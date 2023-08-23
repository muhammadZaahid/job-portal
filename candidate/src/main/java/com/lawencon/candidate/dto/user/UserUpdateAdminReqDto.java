package com.lawencon.candidate.dto.user;

import com.lawencon.candidate.dto.file.FileReqDto;

public class UserUpdateAdminReqDto {
    private String candidateCode;
    private String nik;
	private String name;
	private String phone;
	private String birthPlace;
	private String birthDate;
	private String socmed1;
	private String socmed2;
	private String socmed3;
	private Double experienceYear;
	private Double salaryExpectation;
	private FileReqDto photo;
	private FileReqDto resume;
    
    

    public UserUpdateAdminReqDto(String candidateCode, String nik, String name, String phone,
            String birthPlace, String birthDate, String socmed1, String socmed2, String socmed3, Double experienceYear,
            Double salaryExpectation, FileReqDto photo, FileReqDto resume) {
        this.candidateCode = candidateCode;
        this.nik = nik;
        this.name = name;
        this.phone = phone;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.socmed1 = socmed1;
        this.socmed2 = socmed2;
        this.socmed3 = socmed3;
        this.experienceYear = experienceYear;
        this.salaryExpectation = salaryExpectation;
        this.photo = photo;
        this.resume = resume;
    }
    public String getCandidateCode() {
        return candidateCode;
    }
    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }
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
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
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
    public FileReqDto getPhoto() {
        return photo;
    }
    public void setPhoto(FileReqDto photo) {
        this.photo = photo;
    }
    public FileReqDto getResume() {
        return resume;
    }
    public void setResume(FileReqDto resume) {
        this.resume = resume;
    }
}
