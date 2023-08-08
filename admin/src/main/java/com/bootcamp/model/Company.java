package com.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bootcamp.base.BaseEntity;

@Entity
@Table(name = "t_company")
public class Company extends BaseEntity {

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_desc", nullable = false)
    private String companyDesc;

    @Column(name = "company_tax_number", nullable = false)
    private String companyTaxNumber;

    @Column(name = "company_photo_id", nullable = false)
    private File file;
}
