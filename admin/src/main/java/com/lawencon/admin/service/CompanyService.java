package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyInsertReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	FileDao fileDao;
	

	public InsertResDto createCompany(CompanyInsertReqDto request) {
		
		InsertResDto response = new InsertResDto();
		
		Company company = new Company();
		company.setCompanyCode(generateUniqueProductCode());
		company.setCompanyName(request.getCompanyName());
		company.setCompanyTaxNumber(request.getCompanyTaxNumber());
		company.setCompanyDesc(request.getCompanyDesc());
		
		
		if(request.getCompanyLogo() != null) {
			File fileLogo =  new File();			
			fileLogo.setFiles(request.getCompanyLogo().getFiles());
			fileLogo.setFileFormat(request.getCompanyLogo().getFileFormat());
			fileDao.save(fileLogo);
			company.setCompanyLogo(fileLogo);
		}
		
		if(request.getCompanyBanner() != null) {
			File fileBanner =  new File();			
			fileBanner.setFiles(request.getCompanyBanner().getFiles());
			fileBanner.setFileFormat(request.getCompanyBanner().getFileFormat());
			fileDao.save(fileBanner);
			company.setCompanyBanner(fileBanner);
		}
		
		Company createdCompany = companyDao.save(company);
		
		if(createdCompany != null) {
			response.setId(createdCompany.getId());
			response.setMessage("Company Created Successfully!");
		}

		
		return response;
		
		
	}
	
	public List<CompanyResDto> getAllCompany() {
		
		List<CompanyResDto> responses = new ArrayList<>(); 
		
		companyDao.getAll(Company.class).forEach(c -> {
			CompanyResDto response = new CompanyResDto();
			
			response.setCompanyName(c.getCompanyName());
			
		});
		
		;
		return responses;
	}
	
	private String generateUniqueProductCode() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }
}

