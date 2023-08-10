package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyBannerDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.CompanyLogoDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyInsertReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.CompanyBanner;
import com.lawencon.admin.model.CompanyLogo;
import com.lawencon.admin.model.File;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	FileDao fileDao;
	
	@Autowired
	CompanyLogoDao companyLogoDao;
	
	@Autowired
	CompanyBannerDao companyBannerDao;
	

	public InsertResDto createCompany(CompanyInsertReqDto request) {
		
		InsertResDto response = new InsertResDto();
		
		CompanyLogo companyLogo = new CompanyLogo();
		CompanyBanner companyBanner = new CompanyBanner();
		
		
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
			companyLogo.setFile(fileLogo);
		}
		
		if(request.getCompanyBanner() != null) {
			File fileBanner =  new File();			
			fileBanner.setFiles(request.getCompanyBanner().getFiles());
			fileBanner.setFileFormat(request.getCompanyBanner().getFileFormat());
			fileDao.save(fileBanner);
			company.setCompanyBanner(fileBanner);
			companyBanner.setFile(fileBanner);
		}
		
		Company createdCompany = companyDao.save(company);
		
		if(createdCompany != null) {
			if(request.getCompanyLogo() != null) {
				companyLogo.setCompany(createdCompany);
				companyLogoDao.save(companyLogo);
			}
			
			if(request.getCompanyBanner() != null) {
				companyBanner.setCompany(createdCompany);
				companyBannerDao.save(companyBanner);
			}
			response.setId(createdCompany.getId());
			response.setMessage("Company Created Successfully!");
		}

		
		return response;
		
		
	}
	
	public List<CompanyResDto> getAllCompany() {
		
		List<CompanyResDto> responses = new ArrayList<>(); 
		
		companyDao.getAll(Company.class).forEach(c -> {
			CompanyResDto response = new CompanyResDto();
			
			response.setId(c.getId());
			response.setCompanyCode(c.getCompanyCode());
			response.setCompanyName(c.getCompanyName());
			response.setCompanyTaxNumber(c.getCompanyTaxNumber());
			response.setCompanyDesc(c.getCompanyDesc());
			response.setCompanyBannerId(c.getCompanyBanner().getId());
			response.setCompanyLogoId(c.getCompanyLogo().getId());
						
			responses.add(response);
			
		});
		
		;
		return responses;
	}
	
	private String generateUniqueProductCode() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }
}

