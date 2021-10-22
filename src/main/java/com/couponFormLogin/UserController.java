package com.couponFormLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private SecurityServiceImpl ss;
	
	@GetMapping("/")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}
	
	@PostMapping("/tryLogin")
	public String login(@RequestParam(name = "email")String email,@RequestParam(name = "password")String password) { //parameter should match the name in form or use requestParam annotation
		
		System.out.println(email+" kkkkk");
		boolean res =ss.login(email, password);
		
		if(res) {
			return "index";
		}
		else{
			return "login";
		}
		
	}
	
}

