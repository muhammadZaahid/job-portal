package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.BlacklistDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklist.BlacklistInsertReqDto;
import com.lawencon.admin.model.Blacklist;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.Company;
import com.lawencon.base.ConnHandler;

@Service
public class BlacklistService {
    @Autowired
    BlacklistDao blacklistDao;
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    CompanyDao companyDao;

    public InsertResDto insert(BlacklistInsertReqDto data){
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        Candidate candidate = candidateDao.getById(Candidate.class, data.getCandidateId());
        Company company = companyDao.getById(Company.class, data.getCompanyId());

        Blacklist blacklist = new Blacklist();
        blacklist.setCandidate(candidate);
        blacklist.setCompany(company);

        Blacklist createdBlacklist = blacklistDao.save(blacklist);
        if(createdBlacklist != null){
            ConnHandler.commit();
            response.setId(createdBlacklist.getId());
            response.setMessage("Success add Candidate to Blacklist!");
        }else{
            ConnHandler.rollback();
        }

        return response;
    }

    public void checkBlacklist(String candidateId, String companyId){
        if(blacklistDao.isBlacklist(candidateId, companyId)){
            throw new RuntimeException("You are blacklisted from this Company!");
        }
    }
}
