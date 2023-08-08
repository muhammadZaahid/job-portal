package com.bootcamp.candidate.model;

import javax.persistence.Column;

import com.bootcamp.candidate.base.BaseEntity;

public class Religion extends BaseEntity{
    
    @Column(name="religion_name", nullable = false)
    private String religionName;

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    
}
