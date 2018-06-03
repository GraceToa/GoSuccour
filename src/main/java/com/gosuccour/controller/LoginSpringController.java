package com.gosuccour.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gosuccour.entity.Client;
import com.gosuccour.entity.User;
import com.gosuccour.service.IClientService;

@Controller
@SessionAttributes("user")
public class LoginSpringController {

	@Autowired
	IClientService clientService;

	/* se tendria que guardar la contraseña cifrada */
	@RequestMapping(value = "/loginFirst", method = RequestMethod.POST)
	public String saveLoginFirstUser(Model model, @Valid User user, RedirectAttributes flash) {
		User userFind = clientService.findOneUser(user.getClientId());
		if (userFind != null) {
			clientService.deleteUser(userFind.getId());
		}
		clientService.saveUser(user);
		model.addAttribute("msg", "User has registered OK¡¡");
		return "login/login";

	}

	@RequestMapping(value = "/login")
	public String goLogin(Map<String, Object> model) {
		model.put("titul", "Login");
		return "login/login";
	}

	@RequestMapping(value = "/findClient", method = RequestMethod.POST)
	public String checkLoginBD(Map<String, Object> model, @RequestParam Map<String, String> reqPar,
			RedirectAttributes flash) {
		String username = reqPar.get("username");
		String password = reqPar.get("password");
		int checkLogin = clientService.checkLogin(username, password);

		if (checkLogin == 1) {
			if (username.equals("admin")) {
			
				return "redirect:clients";
			} else {
				Client client = clientService.getClient(username);
				model.put("client", client);
				model.put("titul", "Welcome " + client.getSurname());
				return "seeClient";
			}

		} else if (checkLogin == 2) {
			model.put("msg", "User ok, password wrong¡");
			return "login/login";
		}
		model.put("msg", "User no exist BD¡");
		return "login/login";
	}
}
