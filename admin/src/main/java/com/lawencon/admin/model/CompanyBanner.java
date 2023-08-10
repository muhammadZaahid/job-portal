package com.lawencon.admin.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_company_banner")
public class CompanyBanner extends BaseEntity{

    @OneToOne
    @JoinColumn(name="company_id")
    private Company companyId;

    @OneToOne
    @JoinColumn(name="file_id")
    private File fileId;

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public File getFileId() {
        return fileId;
    }

    public void setFileId(File fileId) {
        this.fileId = fileId;
    }

    
}
