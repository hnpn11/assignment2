package com.fpt.assignment2.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.assignment2.CustomMessageError;
import com.fpt.assignment2.dto.FileDownloadRequestDTO;
import com.fpt.assignment2.dto.FileUploadResponseDTO;

public interface FileManagementService {
	
	public FileUploadResponseDTO upload(MultipartFile file, Authentication authentication) throws IOException, CustomMessageError;
	
	public Resource download(FileDownloadRequestDTO fileDownloadRequestDTO, Authentication authentication) throws CustomMessageError, MalformedURLException;
}
