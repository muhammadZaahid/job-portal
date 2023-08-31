package com.lawencon.admin.model;

import javax.persistence.*;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_company_banner",uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id"}),@UniqueConstraint(columnNames = {"file_id"})})
public class CompanyBanner extends BaseEntity{

    @OneToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name="file_id")
    private File file;



	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

   

    
}
