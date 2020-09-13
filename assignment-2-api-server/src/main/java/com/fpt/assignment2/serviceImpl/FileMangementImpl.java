package com.fpt.assignment2.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.assignment2.CustomMessageError;
import com.fpt.assignment2.ErrorMessage;
import com.fpt.assignment2.dto.FileDownloadRequestDTO;
import com.fpt.assignment2.dto.FileUploadResponseDTO;
import com.fpt.assignment2.entity.FileManagement;
import com.fpt.assignment2.entity.User;
import com.fpt.assignment2.repository.FileManagementRepository;
import com.fpt.assignment2.service.FileManagementService;
import com.fpt.assignment2.service.UserService;

@Service
public class FileMangementImpl implements FileManagementService {

	@Autowired
	FileManagementRepository fileManagementRepo;

	@Autowired
	UserService userService;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Value("${file.home_directory}")
	private String home;

	@Override
	public FileUploadResponseDTO upload(MultipartFile file, Authentication authentication)
			throws IOException, CustomMessageError {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String username = authentication.getPrincipal().toString();

		User user = new User();
		int userID = userService.findByUsername(username).getUserID();
		user.setUserId(userID);

		if (!checkFileExisted(fileName, userID)) {
			String storePath = home + uploadDir + File.separator + username;

			// Copy content of upload file to server
			Files.createDirectories(Paths.get(storePath));
			Path fileUpload = Paths.get(storePath).resolve(Objects.requireNonNull(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), fileUpload, StandardCopyOption.REPLACE_EXISTING);

			FileManagement fileManagement = new FileManagement();
			fileManagement.setFilePath(storePath);
			fileManagement.setFileName(fileName);
			fileManagement.setUser(user);

			Set<FileManagement> lstFile = user.getLstFile();
			lstFile.add(fileManagement);

			fileManagementRepo.save(fileManagement);

			FileUploadResponseDTO response = new FileUploadResponseDTO(fileName,
					authentication.getPrincipal().toString(), file.getSize());
			return response;
		} else {
			throw new CustomMessageError(new ErrorMessage("File is existed"), HttpStatus.CONFLICT);
		}
	}

	@Override
	public Resource download(FileDownloadRequestDTO fileDownloadRequestDTO, Authentication authentication)
			throws CustomMessageError, MalformedURLException {
		String username = authentication.getPrincipal().toString();
		int userID = userService.findByUsername(username).getUserID();
		String fileName = fileDownloadRequestDTO.getFileName();
		if (checkFileExisted(fileName, userID)) {
			FileManagement fileManage = fileManagementRepo.findFileMangementByFileNameAndUser(fileName, userID);
			String filePath = fileManage.getFilePath() + File.separator + fileManage.getFileName();
			Resource resource = new UrlResource(Paths.get(filePath).toUri());
			if (resource.exists()) {
				return resource;
            } else {
            	throw new CustomMessageError(new ErrorMessage("File is not existed"), HttpStatus.NOT_FOUND);
            }
		} else {
			throw new CustomMessageError(new ErrorMessage("File is not existed"), HttpStatus.NOT_FOUND);
		}
	}

	private boolean checkFileExisted(String fileName, int userID) throws CustomMessageError {
		if (null != fileManagementRepo.findFileMangementByFileNameAndUser(fileName, userID)) {
			return true;
		} else {
			return false;
		}
	}
}
