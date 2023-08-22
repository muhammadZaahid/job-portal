package com.lawencon.admin.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.OfferDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.offer.OfferingInsertReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Offer;
import com.lawencon.admin.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class OfferService {
    @Autowired
    PrincipalService principalService;
    @Autowired
    ApplicantService applicantService;
    @Autowired
    ApplicantDao applicantDao;
    @Autowired
    OfferDao offerDao;
    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    UserDao userDao;

    public InsertResDto insertOffer(OfferingInsertReqDto data) {
        final InsertResDto response = new InsertResDto();

        Applicant applicant = applicantDao.getById(Applicant.class, data.getApplicantId());
        User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
        if (data.getOfferSalary() < applicant.getJobVacancy().getSalaryFrom()){
            throw new RuntimeException("Salary is less than the minimum advertised range!");
        }
        if (applicant.getCurrentStage().equals("mcu")) {
            Offer offer = new Offer();
            offer.setApplicant(applicant);
            offer.setOfferBasicSalary(data.getOfferSalary());
            offer.setOfferLastEmailSend(LocalDateTime.now());
            offer.setOfferPic(user);
            offer.setOfferTime(LocalDateTime.now());

            Offer createdOffer = offerDao.save(offer);

            if (createdOffer != null) {
                UpdateResDto update = applicantService.updateApplicant(data.getApplicantId());
                if (update.getMessage().toLowerCase().contains("success")) {
                    response.setId(createdOffer.getId());
                    response.setMessage("Success create Offering to Candidate");
                }
            } else {
                throw new RuntimeException("Please fill all the data");
            }
        } else {
            throw new RuntimeException("This candidate cannot be offered yet!");
        }

        return response;
    }
}
