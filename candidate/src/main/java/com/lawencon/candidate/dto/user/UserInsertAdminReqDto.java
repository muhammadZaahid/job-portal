package com.lawencon.candidate.dto.user;

public class UserInsertAdminReqDto {
    private String email;
    private String fullName;
    private String candidateCode;

    public UserInsertAdminReqDto(String email, String fullName, String candidateCode) {
        this.email = email;
        this.fullName = fullName;
        this.candidateCode = candidateCode;
    }
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
