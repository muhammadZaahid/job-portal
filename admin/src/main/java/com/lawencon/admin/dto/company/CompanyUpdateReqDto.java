package com.lawencon.admin.dto.company;

import com.lawencon.admin.dto.file.FileReqDto;

public class CompanyUpdateReqDto {

	private Long companyId;
	private String companyCode;
	private String companyName;
	private String companyDesc;
	private String companyTaxNumber;
	private FileReqDto companyLogo;
	private FileReqDto companyBanner;
	
}
