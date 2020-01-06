package com.stark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stark.model.FileUploader;

@Repository
public interface FileUploadeRepository extends JpaRepository<FileUploader, String>{

}
