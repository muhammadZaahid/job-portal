package com.bootcamp.model;

import javax.persistence.Column;

import com.lawencon.base.BaseEntity;

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
