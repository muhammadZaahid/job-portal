package com.lawencon.admin.model;

import com.lawencon.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_job_lvl")
public class JobLevel extends BaseEntity {

    @Column(name = "job_lvl_name", nullable = false)
    private String jobLevelName;

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }
}
