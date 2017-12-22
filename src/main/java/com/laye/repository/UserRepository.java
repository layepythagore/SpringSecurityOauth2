package com.laye.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laye.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
