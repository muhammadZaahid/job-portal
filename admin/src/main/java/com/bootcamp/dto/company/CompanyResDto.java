package com.bootcamp.dto.company;

import com.bootcamp.dto.file.FileResDto;

public class CompanyResDto {

	private Long id;
	private String companyCode;
	private String companyName;
	private String companyDesc;
	private String companyTaxNumber;
	private FileResDto companyLogo;
	private FileResDto companyBanner;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyDesc() {
		return companyDesc;
	}
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
	public String getCompanyTaxNumber() {
		return companyTaxNumber;
	}
	public void setCompanyTaxNumber(String companyTaxNumber) {
		this.companyTaxNumber = companyTaxNumber;
	}
	public FileResDto getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(FileResDto companyLogo) {
		this.companyLogo = companyLogo;
	}
	public FileResDto getCompanyBanner() {
		return companyBanner;
	}
	public void setCompanyBanner(FileResDto companyBanner) {
		this.companyBanner = companyBanner;
	}
	
	
}
