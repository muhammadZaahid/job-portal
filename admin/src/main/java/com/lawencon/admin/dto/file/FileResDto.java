package com.lawencon.admin.dto.file;

public class FileResDto {
	
	private Long id;
	private String files;
	private String fileFormat;
	
	public String getFiles() {
		return files;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	
	
}
