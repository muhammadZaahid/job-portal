package com.lawencon.admin.constant;

public enum ApiList {
    
    ApiCompany("Company","http://localhost:8081/seeker/companies/"),
    ApiApplicant("Applicant","http://localhost:8081/seeker/applicant/"),
    ApiJobVacancy("JobVacancy","http://localhost:8081/seeker/job-vacancies/");

    public final String apiName;
    public final String apiUrl;

    ApiList(String apiName, String apiUrl){
        this.apiName = apiName;
        this.apiUrl = apiUrl;
    }
}
