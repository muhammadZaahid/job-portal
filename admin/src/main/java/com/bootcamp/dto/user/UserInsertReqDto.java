package com.bootcamp.dto.user;

import javax.validation.constraints.NotBlank;

public class UserInsertReqDto {

	@NotBlank(message = "Email cannot be empty!")
	private String email;
	private String fullname;
	private String phone;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
