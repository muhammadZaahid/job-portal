package com.lawencon.admin.dto.company;

import com.lawencon.admin.dto.file.FileReqDto;
import com.lawencon.admin.model.File;

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
	public FileReqDto getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(FileReqDto companyLogo) {
		this.companyLogo = companyLogo;
	}
	public FileReqDto getCompanyBanner() {
		return companyBanner;
	}
	public void setCompanyBanner(FileReqDto companyBanner) {
		this.companyBanner = companyBanner;
	}
	
	


}
