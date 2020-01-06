package com.stark.servicesImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stark.exception.FileStorageException;
import com.stark.exception.MyFileNotFoundException;
import com.stark.model.FileUploader;
import com.stark.model.Role;
import com.stark.model.User;
import com.stark.repository.FileUploadeRepository;
import com.stark.repository.RoleRepository;
import com.stark.repository.UserRepository;
import com.stark.services.UserService;
import org.springframework.util.StringUtils;

@Service
public class UserserviceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private FileUploadeRepository fileUploadeRepository;

	@Override
	public void save(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role role = roleRepository.getOne(1);
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> findByEmail = userRepository.findByEmail(email);
		if(!findByEmail.isPresent()) {
			return null;
		}
		return findByEmail.get();
	}

	@Override
	public FileUploader storeFile(MultipartFile file) {
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }
	            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            User user = userRepository.findByUserName(userDetails.getUsername());
	            FileUploader dbFile = new FileUploader(fileName, file.getContentType(), file.getBytes(),user);
	            return fileUploadeRepository.save(dbFile);
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
			}

	@Override
	public FileUploader getFile(String fileId) {
		return fileUploadeRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

}
