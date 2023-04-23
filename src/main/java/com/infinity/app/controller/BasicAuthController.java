package com.infinity.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthController {
	
	@GetMapping(path= "/basicAuth/")
	public String basicAuth() {
		return "Welcome Lola.";
	}	
	

}
