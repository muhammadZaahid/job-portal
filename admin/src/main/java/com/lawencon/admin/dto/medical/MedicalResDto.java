package com.lawencon.admin.dto.medical;

import com.lawencon.admin.dto.file.FileReqDto;

public class MedicalResDto {
    private String medicalLocation;
    private String medicalDate;
    private String medicalNotes;
    private FileReqDto medicalFile;

    public String getMedicalLocation() {
        return medicalLocation;
    }

    public void setMedicalLocation(String medicalLocation) {
        this.medicalLocation = medicalLocation;
    }

    public String getMedicalDate() {
        return medicalDate;
    }

    public void setMedicalDate(String medicalDate) {
        this.medicalDate = medicalDate;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public FileReqDto getMedicalFile() {
        return medicalFile;
    }

    public void setMedicalFile(FileReqDto medicalFile) {
        this.medicalFile = medicalFile;
    }
}
