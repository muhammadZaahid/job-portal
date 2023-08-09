package com.bootcamp.dto.company;

import com.bootcamp.dto.file.FileReqDto;
import com.bootcamp.model.File;

public class CompanyInsertReqDto {
	
	private String companyCode;
	private String companyName;
	private String companyDesc;
	private String companyTaxNumber;
	private FileReqDto companyLogo;
	private FileReqDto companyBanner;
	
	
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
	public File getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(File companyLogo) {
		this.companyLogo = companyLogo;
	}
	public File getCompanyBanner() {
		return companyBanner;
	}
	public void setCompanyBanner(File companyBanner) {
		this.companyBanner = companyBanner;
	}
	
	

}
