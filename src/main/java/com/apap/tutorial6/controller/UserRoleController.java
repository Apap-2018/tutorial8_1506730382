package com.apap.tutorial6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	private String changeUserPassword(String username, Model model) {
		UserRoleModel user = userService.getUserByUsername(username);
		model.addAttribute("user", user);
		return "change-password";
	}
	
	@RequestMapping(value = "/passwordChanged", method = RequestMethod.POST)
	private String changeUserPassword(long id, String passwordOld, String passwordNew, String passwordNewConfirm, Model model) {
		UserRoleModel user = userService.getUserById(id);
		model.addAttribute("user", user);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(passwordOld, user.getPassword()) && passwordNew.equals(passwordNewConfirm) && passwordNew.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
			user.setPassword(passwordNew);
			userService.addUser(user);
			return "update";
		} else {
			return "error-500";
		}
		
	}
}