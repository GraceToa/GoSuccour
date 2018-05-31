package com.gosuccour.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gosuccour.entity.Coordenadas;
import com.gosuccour.entity.LocationClient;
import com.gosuccour.entity.Token;
import com.gosuccour.service.IClientService;

@RestController
@RequestMapping("/api")
public class AndroidControllerRestApi {

	@Autowired
	IClientService clientService;

	static HashMap<String, String> listToken = new HashMap<String, String>();

	@RequestMapping("/login")
	public Token authentication(Model model, @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		System.out.println(username);
		System.out.println(password);
		int checkLogin = clientService.checkLogin(username, password);
		if (checkLogin == 1) {
			System.out.println("Welcome User !");
			Token token = new Token();
			listToken.put(username, token.getUuid());
			for (Map.Entry<String, String> entry : listToken.entrySet()) {
				System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
			}
			return token;
		} else if (checkLogin == 3) {
			System.out.println("User not exists");
		} else {
			System.out.println("Incorrect User / Password");
		}
		return null;
	}
	
//	@PostMapping("/locationClient")
//	public Coordenadas getLocation(@RequestBody LocationClient locationClient ) {
//		clientService.saveLocationClient(locationClient);	
//		return l;
//		
//	}
	
	
	
	
}
