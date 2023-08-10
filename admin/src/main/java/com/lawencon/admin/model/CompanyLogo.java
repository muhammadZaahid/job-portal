package com.lawencon.admin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_company_logo")
public class CompanyLogo extends BaseEntity{
	
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
