package Sevg.CrudSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/LoginPage")
	public String LoginPage() {
		
		return "fancy-login";
		
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
}
