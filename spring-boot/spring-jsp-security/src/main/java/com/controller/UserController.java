package com.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.repository.UserRepository;
import com.request.EditUser;
import com.util.WebUtils;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("user-info")
	public String userInfo(Model model, Principal principal) {
		// Cach 1: Sau khi user login thanh cong se co principal
		if (principal != null) {
			System.out.println("From Principal");
			System.out.println("Username: " + principal.getName());
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		// Cach 2
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object object = auth.getPrincipal();
		if (object instanceof org.springframework.security.core.userdetails.User) {
			org.springframework.security.core.userdetails.User user = (User) object;
			System.out.println("From SecurityContextHolder");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Role: " + auth.getAuthorities());
		}
		return "user-info";
	}

	@GetMapping("admin")
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "admin";
	}

	@GetMapping("edit-user")
	private String editUser() {
		return "edit-user";
	}

	@PostMapping("edit-user")
	private String _____editUser(@ModelAttribute EditUser editUser, Principal principal) {
		System.out.println(editUser.toString());
		if (principal != null) {
			Optional<com.entity.User> opt = userRepository.findByUsername(principal.getName());
			userRepository.updateUsername(principal.getName(), editUser.getUsername());
			Collection<SimpleGrantedAuthority> nowAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
					.getContext().getAuthentication().getAuthorities();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					editUser.getUsername(), opt.get().getPassword(), nowAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		return "edit-user";
	}

}
