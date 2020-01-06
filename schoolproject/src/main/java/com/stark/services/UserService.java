package com.stark.services;

import org.springframework.web.multipart.MultipartFile;
import com.stark.model.FileUploader;
import com.stark.model.User;

public interface UserService {

	public void save(User user);

	abstract User findByEmail(String email);

	public abstract FileUploader storeFile(MultipartFile file);

	public abstract FileUploader getFile(String fileId);

}
