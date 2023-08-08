package com.bootcamp.model;

import com.bootcamp.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_job_vacancy")
public class JobVacancy extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "pic_id", nullable = false)
    private User user;

    @Column(name = "job_vacancy_code", nullable = false)
    private String JobVacancyCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "salary_from", nullable = false)
    private String salaryFrom;

    @Column(name = "salary_to", nullable = false)
    private String salaryTo;

    @Column(name = "salary_publish", nullable = false)
    private boolean SalaryPublish;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "stg_application", nullable = false)
    private boolean stgApplication;

    @Column(name = "stg_assessment", nullable = false)
    private boolean stgAssessment;

    @Column(name = "stg_interview", nullable = false)
    private boolean stgInterview;

    @Column(name = "stg_mcu", nullable = false)
    private boolean stgMcu;

    @Column(name = "stg_offer", nullable = false)
    private boolean stgOffer;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJobVacancyCode() {
        return JobVacancyCode;
    }

    public void setJobVacancyCode(String jobVacancyCode) {
        JobVacancyCode = jobVacancyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }

    public boolean isSalaryPublish() {
        return SalaryPublish;
    }

    public void setSalaryPublish(boolean salaryPublish) {
        SalaryPublish = salaryPublish;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isStgApplication() {
        return stgApplication;
    }

    public void setStgApplication(boolean stgApplication) {
        this.stgApplication = stgApplication;
    }

    public boolean isStgAssessment() {
        return stgAssessment;
    }

    public void setStgAssessment(boolean stgAssessment) {
        this.stgAssessment = stgAssessment;
    }

    public boolean isStgInterview() {
        return stgInterview;
    }

    public void setStgInterview(boolean stgInterview) {
        this.stgInterview = stgInterview;
    }

    public boolean isStgMcu() {
        return stgMcu;
    }

    public void setStgMcu(boolean stgMcu) {
        this.stgMcu = stgMcu;
    }

    public boolean isStgOffer() {
        return stgOffer;
    }

    public void setStgOffer(boolean stgOffer) {
        this.stgOffer = stgOffer;
    }
}
