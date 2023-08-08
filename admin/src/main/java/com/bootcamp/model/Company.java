package com.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File companyLogo;

    @OneToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File companyBanner;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyTaxNumber() {
        return companyTaxNumber;
    }

    public void setCompanyTaxNumber(String companyTaxNumber) {
        this.companyTaxNumber = companyTaxNumber;
    }

    public File getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(File companyLogo) {
        this.companyLogo = companyLogo;
    }

    public File getCompanyBanner() {
        return companyBanner;
    }

    public void setCompanyBanner(File companyBanner) {
        this.companyBanner = companyBanner;
    }

    
}
