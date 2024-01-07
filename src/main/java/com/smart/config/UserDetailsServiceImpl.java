package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.entity.User;
import com.smart.repo.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userRepo.findByEmail(username);
		 if(user==null) {
			 throw new UsernameNotFoundException("Could Not Found User!!!");
		 }
		 CustomUserDetails cd = new CustomUserDetails(user);
		 return cd;
	}

}
