package com.apap.tutorial6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
    private String home() {
        return "home";
    }
	
	@RequestMapping("/login")
	private String login() {
		return "login";
	}
}
