package com.fpt.assignment2.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequestDTO {

	private String filePath;
	private String fileName;

	public FileUploadRequestDTO(String filePath, String fileName, MultipartFile file) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public FileUploadRequestDTO(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public FileUploadRequestDTO() {
		super();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
