package com.bootcamp.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

public class FileCV extends BaseEntity{
    
    @OneToOne
    @JoinColumn(name="file_id")
    private File fileId;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidateId;

    public File getFileId() {
        return fileId;
    }

    public void setFileId(File fileId) {
        this.fileId = fileId;
    }

    public Candidate getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidate candidateId) {
        this.candidateId = candidateId;
    }

    
}
