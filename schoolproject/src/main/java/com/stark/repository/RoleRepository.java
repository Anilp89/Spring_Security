package com.stark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stark.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
} 
