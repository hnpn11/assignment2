package com.fpt.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.assignment2.entity.FileManagement;

public interface FileManagementRepository extends JpaRepository<FileManagement, Integer> {

	@Query(value = "select u.file_id, u.file_name, u.file_path, u.user_id from file_mng u where file_name = :fileName AND user_id = :userID", nativeQuery = true)
	FileManagement findFileMangementByFileNameAndUser(@Param("fileName") String fileName, @Param("userID") int userID);
}
