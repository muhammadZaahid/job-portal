package com.bootcamp.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name="t_applicant")
public class Applicant extends BaseEntity {

   @ManyToOne
   @JoinColumn(name = "candidate_id", nullable = false)
   private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_vacancy_id", nullable = false)
    private JobVacancy jobVacancy;

    @Column(name = "job_vacancy_code", nullable = false)
    private String JobVacancyCode;

    @Column(name = "current_stage", nullable = false)
    private boolean currentStage;

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
}
