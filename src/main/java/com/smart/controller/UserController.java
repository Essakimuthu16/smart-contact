package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.entity.Contact;
import com.smart.entity.User;
import com.smart.helper.Message;
import com.smart.repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepo userRepo;

	@GetMapping("/index")
	@PreAuthorize("hasAuthority('USER')")
	public String dashboard(Model model) {
	
		return "normal/user_dashboard";
	}
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact",new Contact());
		return "normal/add_contact_form";
	}
	
	@PostMapping("/process-contact")
	private String processContactForm(@ModelAttribute("contact") Contact contact,
			@RequestParam("profileImage") MultipartFile file,HttpSession session) {
		try {
		User user = userRepo.findByName("Essakimuthu Yadav");
		
		if(file.isEmpty()) {
			System.out.println("File is Empty");
		}else {
			contact.setImage(file.getOriginalFilename());
			File saveFile = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		}
		contact.setUser(user);
		user.getListOfContact().add(contact);
		userRepo.save(user);
		session.setAttribute("message",new Message("Your Contact is added !! Add more...","success"));
		session.removeAttribute("message");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			session.setAttribute("message",new Message("Something went wrong !! Try Again...","danger"));
			session.removeAttribute("message");
		}
		
		return "normal/add_contact_form";
	}
	
}
