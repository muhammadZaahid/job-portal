package com.bootcamp.model;

import javax.persistence.*;

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
    @JoinColumn(name = "company_photo_id", nullable = false)
    private File file;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
