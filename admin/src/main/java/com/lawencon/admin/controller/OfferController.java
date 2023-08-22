package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.offer.OfferingInsertReqDto;
import com.lawencon.admin.dto.offer.OfferingResDto;
import com.lawencon.admin.service.OfferService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/offer")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping
    public ResponseEntity<InsertResDto> insertOffer(@RequestBody OfferingInsertReqDto data){
        InsertResDto response = offerService.insertOffer(data);    
        
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OfferingResDto> getOfferByApplicant(@PathVariable("id")String applicantId){
        final OfferingResDto response = offerService.getByApplicantId(applicantId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // @PatchMapping("/{id}")
}
