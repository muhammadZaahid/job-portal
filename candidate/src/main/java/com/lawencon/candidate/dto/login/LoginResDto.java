package com.lawencon.candidate.dto.login;

public class LoginResDto {
    private String token;
	private String userId;
	private String candidateId;
	private String candidateName;
    private String candidateCode;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String profileName) {
        this.candidateName = profileName;
    }
    public String getCandidateCode() {
        return candidateCode;
    }
    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }
}
