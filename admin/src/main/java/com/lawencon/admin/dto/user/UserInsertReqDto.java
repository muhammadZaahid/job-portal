package com.lawencon.admin.dto.user;

import javax.validation.constraints.NotBlank;

public class UserInsertReqDto {

    @NotBlank(message = "Email masih kosong")
    private String email;
    @NotBlank(message = "Nama lengkap belum terisi")
    private String fullName;
    @NotBlank(message = "Password masih kosong")
    private String password;

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
