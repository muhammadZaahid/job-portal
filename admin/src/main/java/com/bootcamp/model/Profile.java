package com.bootcamp.model;

import com.lawencon.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="t_profile")
public class Profile extends BaseEntity {

    @Column(name="full_name", nullable = false)
    private String name;

    @Column(name="phone", nullable = false)
    private  String phone;

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
}
