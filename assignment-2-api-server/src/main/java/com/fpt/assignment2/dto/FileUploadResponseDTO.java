package com.fpt.assignment2.dto;

public class FileUploadResponseDTO {
	private String fileName;
	private String username;
	private long fileSize;
	public FileUploadResponseDTO(String fileName, String username, long fileSize) {
		super();
		this.fileName = fileName;
		this.username = username;
		this.fileSize = fileSize;
	}
	public FileUploadResponseDTO() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
}
