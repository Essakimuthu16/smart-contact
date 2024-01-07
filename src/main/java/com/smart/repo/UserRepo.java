package com.smart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	User findByEmail(String email);
}
