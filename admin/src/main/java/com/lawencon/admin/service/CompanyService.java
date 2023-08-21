package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.admin.dao.CompanyBannerDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.CompanyLogoDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.company.CompanyInsertReqDto;
import com.lawencon.admin.dto.company.CompanyInsertSeekerReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.dto.company.CompanyUpdateReqDto;
import com.lawencon.admin.dto.company.CompanyUpdateSeekerReqDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.CompanyBanner;
import com.lawencon.admin.model.CompanyLogo;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.GeneratorUtil;

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

	@Autowired
	RestTemplate restTemplate;
	

	public InsertResDto createCompany(CompanyInsertReqDto request) {
		ConnHandler.begin();

		InsertResDto response = new InsertResDto();

		CompanyLogo companyLogo = new CompanyLogo();
		CompanyBanner companyBanner = new CompanyBanner();

		Company company = new Company();
		company.setCompanyCode(GeneratorUtil.generateCode());
		company.setCompanyName(request.getCompanyName());
		company.setCompanyTaxNumber(request.getCompanyTaxNumber());
		company.setCompanyDesc(request.getCompanyDesc());

		if (request.getCompanyLogo() != null) {
			File fileLogo = new File();
			fileLogo.setFiles(request.getCompanyLogo().getFiles());
			fileLogo.setFileFormat(request.getCompanyLogo().getFileFormat());
			fileDao.save(fileLogo);
			company.setCompanyLogo(fileLogo);
			companyLogo.setFile(fileLogo);
		}

		if (request.getCompanyBanner() != null) {
			File fileBanner = new File();
			fileBanner.setFiles(request.getCompanyBanner().getFiles());
			fileBanner.setFileFormat(request.getCompanyBanner().getFileFormat());
			fileDao.save(fileBanner);
			company.setCompanyBanner(fileBanner);
			companyBanner.setFile(fileBanner);
		}

		Company createdCompany = companyDao.save(company);

		if (createdCompany != null) {
			if (request.getCompanyLogo() != null) {
				companyLogo.setCompany(createdCompany);
				companyLogoDao.save(companyLogo);
			}

			if (request.getCompanyBanner() != null) {
				companyBanner.setCompany(createdCompany);
				companyBannerDao.save(companyBanner);
			}

			HttpEntity<CompanyInsertSeekerReqDto> reqBody = new HttpEntity<CompanyInsertSeekerReqDto>(
					new CompanyInsertSeekerReqDto(createdCompany.getCompanyCode(), request.getCompanyName(),
							request.getCompanyDesc(), request.getCompanyTaxNumber(), request.getCompanyLogo(),
							request.getCompanyBanner()));
			ResponseEntity<InsertResDto> res = restTemplate.exchange("http://localhost:8081/seeker/companies",
					HttpMethod.POST, reqBody, InsertResDto.class);
			if (res.getStatusCodeValue() == 201) {
				ConnHandler.commit();
				response.setId(createdCompany.getId());
				response.setMessage("Company Created Successfully!");
			}else{
				ConnHandler.rollback();
			}
		}else{
			ConnHandler.rollback();
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
			response.setCompanyBannerId(companyBannerDao.getCompanyBannerByCompanyId(c.getId()));
			response.setCompanyLogoId(companyLogoDao.getCompanyLogoByCompanyId(c.getId()));

			responses.add(response);

		});

		return responses;
	}
	
	public CompanyResDto getCompanyById(String companyId) {
		final CompanyResDto response = new CompanyResDto();
		Company c = companyDao.getById(Company.class, companyId);
			response.setId(c.getId());
			response.setCompanyCode(c.getCompanyCode());
			response.setCompanyName(c.getCompanyName());
			response.setCompanyTaxNumber(c.getCompanyTaxNumber());
			response.setCompanyDesc(c.getCompanyDesc());
			response.setCompanyBannerId(companyBannerDao.getCompanyBannerByCompanyId(c.getId()));
			response.setCompanyLogoId(companyLogoDao.getCompanyLogoByCompanyId(c.getId()));

		return response;
	}

	public UpdateResDto updateCompany(CompanyUpdateReqDto request) {
		
		ConnHandler.begin();
		
		final UpdateResDto updateResDto = new UpdateResDto();
			
		final Company company = companyDao.getById(Company.class,request.getCompanyId());
		company.setCompanyName(request.getCompanyName());
		company.setCompanyTaxNumber(request.getCompanyTaxNumber());
		company.setCompanyDesc(request.getCompanyDesc());	
		
		if (request.getCompanyLogo().getFiles() != null) {
			CompanyLogo companyLogo = companyLogoDao.getByCompanyId(request.getCompanyId());
			File fileLogo = fileDao.getById(File.class, companyLogo.getFile().getId());
			fileLogo.setFiles(request.getCompanyLogo().getFiles());
			fileLogo.setFileFormat(request.getCompanyLogo().getFileFormat());
			fileDao.save(fileLogo);
			company.setCompanyLogo(fileLogo);
			companyLogo.setFile(fileLogo);
			companyLogoDao.save(companyLogo);
		}

		if (request.getCompanyBanner().getFiles() != null) {
			CompanyBanner companyBanner = companyBannerDao.getByCompanyId(request.getCompanyId());
			File fileBanner = fileDao.getById(File.class, companyBanner.getFile().getId());
			fileBanner.setFiles(request.getCompanyBanner().getFiles());
			fileBanner.setFileFormat(request.getCompanyBanner().getFileFormat());
			fileDao.save(fileBanner);
			company.setCompanyBanner(fileBanner);
			companyBanner.setFile(fileBanner);
			companyBannerDao.save(companyBanner);
		}
		
		company.setVersion(company.getVersion() + 1);
		
		Company updatedCompany = companyDao.save(company);
		
		if(updatedCompany != null) {
			
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CompanyUpdateSeekerReqDto> reqBody = new HttpEntity<CompanyUpdateSeekerReqDto>(
					new CompanyUpdateSeekerReqDto(
							company.getCompanyCode(),
							request.getCompanyName(),
							request.getCompanyDesc(),
							request.getCompanyTaxNumber(),	
							request.getCompanyLogo(),
							request.getCompanyBanner()
							)
					);
			
			ResponseEntity<UpdateResDto> updateRes = restTemplate.exchange(
					"http://localhost:8081/seeker/companies",
					HttpMethod.PUT,
					reqBody,
					UpdateResDto.class
					);
			
			if(updateRes.getStatusCode().equals(HttpStatus.OK)) {
				ConnHandler.commit();
				updateResDto.setVer(updatedCompany.getVersion());
				updateResDto.setMessage("Company Updated Successfully");
				
			}else {
				ConnHandler.rollback();
				throw new RuntimeException();
			}
			
		}
		
		
		return updateResDto;
	}
}
