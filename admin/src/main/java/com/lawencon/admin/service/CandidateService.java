package com.lawencon.admin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.ConstantDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.CountResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidate.CandidateResDto;
import com.lawencon.admin.dto.candidate.CandidateSeekerInsertReqDto;
import com.lawencon.admin.dto.candidate.CandidateSeekerUpdateReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.GeneratorUtil;

@Service
public class CandidateService {

	@Autowired
	CandidateDao candidateDao;

	@Autowired
	FileDao fileDao;
	
	@Autowired
	ConstantDao constantDao;

	public InsertResDto createCandidate(CandidateInsertReqDto request) {

		InsertResDto response = new InsertResDto();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate = LocalDate.parse(request.getBirthDate(), formatter);

		Candidate candidate = new Candidate();
		candidate.setNik(request.getNik());
		candidate.setName(request.getName());
		candidate.setEmail(request.getEmail());
		candidate.setPhone(request.getPhone());
		candidate.setBirthPlace(request.getBirthPlace());
		candidate.setBirthDate(birthDate);
		candidate.setSocmed1(request.getSocmed1());
		candidate.setSocmed2(request.getSocmed2());
		candidate.setSocmed3(request.getSocmed3());
		candidate.setExperienceYear(request.getExperienceYear());
		candidate.setSalaryExpectation(request.getSalaryExpectation());

		if (request.getPhoto() != null) {
			File photo = new File();
			photo.setFiles(request.getPhoto().getFiles());
			photo.setFileFormat(request.getPhoto().getFileFormat());
			fileDao.save(photo);
			candidate.setPhoto(photo);
		}

		File resume = new File();
		resume.setFiles(request.getResume().getFiles());
		resume.setFileFormat(request.getResume().getFileFormat());
		fileDao.save(resume);
		candidate.setResume(resume);
		candidate.setCandidateCode(GeneratorUtil.generateCode());

		Candidate createdCandidate = candidateDao.save(candidate);

		if (createdCandidate != null) {

			response.setId(createdCandidate.getId());
			response.setMessage("Candidate Created Successfully");
		}

		return response;
	}

	public InsertResDto saveCandidateFromSeeker(CandidateSeekerInsertReqDto data) {
		final InsertResDto response = new InsertResDto();

		Candidate candidate = new Candidate();
		candidate.setEmail(data.getEmail());
		candidate.setName(data.getFullName());
		candidate.setCandidateCode(data.getCandidateCode());

		Supplier<String> supplier = () -> "system";

		Candidate createdCandidate = candidateDao.saveNoLogin(candidate, supplier);

		if (createdCandidate != null) {
			response.setId(createdCandidate.getId());
			response.setMessage("Success create candidate in Admin");
		}

		return response;
	}

	public List<CandidateResDto> getAllCandidate() {

		List<CandidateResDto> response = new ArrayList<>();

		candidateDao.getAll(Candidate.class).forEach(c -> {
			CandidateResDto candidateData = new CandidateResDto();

			candidateData.setId(c.getId());
			candidateData.setNik(c.getNik());
			candidateData.setName(c.getName());
			candidateData.setEmail(c.getEmail());
			candidateData.setPhone(c.getPhone());
			candidateData.setBirthPlace(c.getBirthPlace());
			if (c.getBirthDate() == null) {
				candidateData.setBirthDate(null);
			} else {
				candidateData.setBirthDate(c.getBirthDate().toString());
			}
			candidateData.setSocmed1(c.getSocmed1());
			candidateData.setSocmed2(c.getSocmed2());
			candidateData.setSocmed3(c.getSocmed3());
			candidateData.setExperienceYear(c.getExperienceYear());
			candidateData.setSalaryExpectation(c.getSalaryExpectation());
			if (c.getPhoto() == null) {
				candidateData.setPhotoId(null);
			} else {
				candidateData.setPhotoId(c.getPhoto().getId());
			}
			if (c.getResume() == null) {
				candidateData.setResumeId(null);
			} else {
				candidateData.setResumeId(c.getResume().getId());
			}

			response.add(candidateData);

		});

		return response;
	}

	public CandidateResDto getByCandidateId(String candidateId) {
		CandidateResDto response = new CandidateResDto();
		Candidate candidate = candidateDao.getById(Candidate.class, candidateId);
		response.setId(candidate.getId());
		response.setNik(candidate.getNik());
		response.setName(candidate.getName());
		response.setEmail(candidate.getEmail());
		response.setPhone(candidate.getPhone());
		response.setBirthPlace(candidate.getBirthPlace());
		if (candidate.getBirthDate() == null) {
			response.setBirthDate(null);
		} else {
			response.setBirthDate(candidate.getBirthDate().toString());
		}
		response.setSocmed1(candidate.getSocmed1());
		response.setSocmed2(candidate.getSocmed2());
		response.setSocmed3(candidate.getSocmed3());
		response.setExperienceYear(candidate.getExperienceYear());
		response.setSalaryExpectation(candidate.getSalaryExpectation());
		if (candidate.getPhoto() == null) {
			response.setPhotoId(null);
		} else {
			response.setPhotoId(candidate.getPhoto().getId());
		}
		if (candidate.getResume() == null) {
			response.setResumeId(null);
		} else {
			response.setResumeId(candidate.getResume().getId());
		}

		return response;
	}

	public UpdateResDto updateFromSeeker(CandidateSeekerUpdateReqDto data) {
		ConnHandler.begin();
		final UpdateResDto response = new UpdateResDto();
		Supplier<String> supplier = () -> "df9351e2-9343-440b-99ce-c4af4ce9f767";
		try {
			Candidate candidate = candidateDao.getByCode(data.getCandidateCode());
			candidate.setNik(data.getNik());
			candidate.setName(data.getName());
			candidate.setPhone(data.getPhone());
			candidate.setBirthPlace(data.getBirthPlace());
			candidate.setBirthDate(LocalDate.parse(data.getBirthDate()));
			candidate.setSocmed1(data.getSocmed1());
			candidate.setSocmed2(data.getSocmed2());
			candidate.setSocmed3(data.getSocmed3());
			candidate.setExperienceYear(data.getExperienceYear());
			candidate.setSalaryExpectation(data.getSalaryExpectation());

			Boolean isPhoto = false;
			Boolean isResume = false;

			if (candidate.getPhoto() != null) {
				isPhoto = true;
			}

			if (candidate.getResume() != null) {
				isResume = true;
			}
			if (data.getPhoto().getFiles() != null && !data.getPhoto().getFiles().isEmpty()) {
				if (isPhoto) {
					File filePhoto = fileDao.getById(File.class, candidate.getPhoto().getId());
					filePhoto.setFiles(data.getPhoto().getFiles());
					filePhoto.setFileFormat(data.getPhoto().getFileFormat());
					filePhoto.setVersion(filePhoto.getVersion() + 1);
					fileDao.saveNoLogin(filePhoto, supplier);
					candidate.setPhoto(filePhoto);
				} else {
					File filePhoto = new File();
					filePhoto.setFiles(data.getPhoto().getFiles());
					filePhoto.setFileFormat(data.getPhoto().getFileFormat());
					fileDao.saveNoLogin(filePhoto, supplier);
					candidate.setPhoto(filePhoto);
				}
			}
			if (data.getResume().getFiles() != null && !data.getResume().getFiles().isEmpty()) {
				if (isResume) {
					File fileResume = fileDao.getById(File.class, candidate.getResume().getId());
					fileResume.setFiles(data.getResume().getFiles());
					fileResume.setFileFormat(data.getResume().getFileFormat());
					fileResume.setVersion(fileResume.getVersion() + 1);
					fileDao.saveNoLogin(fileResume, supplier);
					candidate.setResume(fileResume);
				} else {
					File fileResume = new File();
					fileResume.setFiles(data.getResume().getFiles());
					fileResume.setFileFormat(data.getResume().getFileFormat());
					fileDao.saveNoLogin(fileResume, supplier);
					candidate.setResume(fileResume);
				}
			}
			Candidate upCandidate = candidateDao.saveNoLogin(candidate, supplier);
			ConnHandler.commit();
			response.setVer(upCandidate.getVersion());
			response.setMessage("Success update candidate data!");
		} catch (Exception e) {
			ConnHandler.rollback();
			e.printStackTrace();
			throw new RuntimeException("Error while updating candidate's data!");
		}

		return response;
	}
	
	public CountResDto getTotalCandidate() {		
		CountResDto response = new CountResDto();
		String total = constantDao.getTotal("t_candidate");
		response.setTotal(total);
		
		return response;
	}
}
