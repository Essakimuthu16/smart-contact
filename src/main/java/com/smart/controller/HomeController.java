package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.entity.User;
import com.smart.helper.Message;
import com.smart.repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home ~ Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","About ~ Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("title","Register ~ Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/do_register")
	public String registerUser(HttpSession session,@ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue = "false") boolean agreement , Model model ) {
		try {
			
			if(!agreement) {
				System.out.println("You have not agreed to terms and conditions");
				throw new Exception("You have not agreed to terms and conditions");
			}
		System.out.println("Agreement : "+agreement);
		System.out.println("User : "+user);
		
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User result = userRepo.save(user);
		model.addAttribute("title","Register ~ Smart Contact Manager");
		model.addAttribute("user", new User());
		session.setAttribute("message", new Message("Successfully Registered!!!","alert-success"));
		 session.removeAttribute("message");
		return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went Wrong!!  "+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}
}
