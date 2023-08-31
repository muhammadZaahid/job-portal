package com.lawencon.admin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_mcu",uniqueConstraints = {@UniqueConstraint(columnNames = "applicant_id")})
public class Medical extends BaseEntity{
    
    @OneToOne
    @JoinColumn(name="applicant_id")
    private Applicant applicantId;

    @Column(name="medical_location")
    private String medicalLocation;

    @Column(name="medical_date")
    private LocalDate medicalDate;

    @Column(name="medical_notes")
    private String medicalNotes;

    @OneToOne
    @JoinColumn(name="medical_file")
    private File medicalFile;

    public Applicant getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Applicant applicantId) {
        this.applicantId = applicantId;
    }

    public String getMedicalLocation() {
        return medicalLocation;
    }

    public void setMedicalLocation(String medicalLocation) {
        this.medicalLocation = medicalLocation;
    }

    public LocalDate getMedicalDate() {
        return medicalDate;
    }

    public void setMedicalDate(LocalDate medicalDate) {
        this.medicalDate = medicalDate;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public File getMedicalFile() {
        return medicalFile;
    }

    public void setMedicalFile(File medicalFile) {
        this.medicalFile = medicalFile;
    }

}
