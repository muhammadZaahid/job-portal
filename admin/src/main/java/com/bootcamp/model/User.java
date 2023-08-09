package com.bootcamp.model;

import com.lawencon.base.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name="t_user")
public class User extends BaseEntity {

    @OneToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    @Column(name = "email",unique = true, nullable = true)
    private String email;

    @Column(name = "password",nullable = true)
    private String password;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
