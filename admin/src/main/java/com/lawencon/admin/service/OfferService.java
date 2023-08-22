package com.lawencon.admin.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.OfferDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.offer.OfferingInsertReqDto;
import com.lawencon.admin.dto.offer.OfferingResDto;
import com.lawencon.admin.dto.offer.OfferingUpdateReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Offer;
import com.lawencon.admin.model.User;
import com.lawencon.base.ConnHandler;
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

    @Transactional
    public InsertResDto insertOffer(OfferingInsertReqDto data) {
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        Applicant applicant = applicantDao.getById(Applicant.class, data.getApplicantId());
        User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
        if (data.getOfferSalary() < applicant.getJobVacancy().getSalaryFrom()) {
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
                    ConnHandler.commit();
                    response.setId(createdOffer.getId());
                    response.setMessage("Success create Offering to Candidate");
                }else{
                    ConnHandler.rollback();
                }
            } else {
                throw new RuntimeException("Please fill all the data");
            }
        } else {
            throw new RuntimeException("This candidate cannot be offered yet!");
        }

        return response;
    }

    public OfferingResDto getByApplicantId(String applicantId) {
        final OfferingResDto response = new OfferingResDto();

        Offer offer = offerDao.getByApplicantId(applicantId);

        response.setApplicantId(applicantId);
        response.setId(offer.getId());
        response.setOfferLastEmailSend(offer.getOfferLastEmailSend().toString());
        response.setOfferPicName(offer.getOfferPic().getProfile().getName());
        response.setOfferTime(offer.getOfferTime().toString());
        response.setOfferSalary(offer.getOfferBasicSalary());

        return response;
    }

    public UpdateResDto updateOffer(String offerId, OfferingUpdateReqDto data) {
        ConnHandler.begin();
        final UpdateResDto response = new UpdateResDto();
        Offer offer = offerDao.getById(Offer.class, offerId);
        Applicant applicant = applicantDao.getById(Applicant.class, data.getApplicantId());
        User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
        if (data.getOfferSalary() < applicant.getJobVacancy().getSalaryFrom()) {
            throw new RuntimeException("Salary is less than the minimum advertised range!");
        }
        try {

            offer.setOfferBasicSalary(data.getOfferSalary());
            offer.setOfferLastEmailSend(LocalDateTime.now());
            offer.setOfferPic(user);

            offerDao.saveAndFlush(offer);
        } catch (Exception e) {
            ConnHandler.rollback();
            e.printStackTrace();
            throw new RuntimeException("Error Update Offering");
        }

        return response;
    }
}
