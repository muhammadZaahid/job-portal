package com.lawencon.admin.dto.candidate;

import javax.validation.constraints.NotBlank;

public class CandidateSeekerInsertReqDto {
    @NotBlank(message = "Email masih kosong")
    private String email;
    @NotBlank(message = "Nama lengkap belum terisi")
    private String fullName;
    private String candidateCode;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getCandidateCode() {
        return candidateCode;
    }
    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }
}
