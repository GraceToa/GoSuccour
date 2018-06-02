package com.gosuccour.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gosuccour.entity.Client;
import com.gosuccour.entity.User;
import com.gosuccour.service.IClientService;
import com.gosuccour.service.IUploadFileService;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clientService;
	@Autowired
	private IUploadFileService uploadFileService;

	@RequestMapping("/")
	public String getIndex(Model model) {
		return "index";
	}

	@RequestMapping(value = "/formClient")
	public String goFormClient(Map<String, Object> model) {
		Client client = new Client();
		model.put("client", client);
		model.put("titul", "Save");
		return "formClient";
	}

	@RequestMapping(value = "/formClient", method = RequestMethod.POST)
	public String saveFormClient(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titul", "Form Client");
			return "formClient";
		}
		if (!photo.isEmpty()) {
			// para eliminar una photo cuando a sido editada-reemplazada
			if (client.getId() != null && client.getId() > 0 && client.getPhoto() != null
					&& client.getPhoto().length() > 0) {
				uploadFileService.delete(client.getPhoto());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			client.setPhoto(uniqueFilename);
		}
		clientService.save(client);
		User user = new User();
		user.setClientId(client.getId());
		model.addAttribute("titul", " Hello " + client.getSurname() + ", " + " " + "choose Username and Password");
		model.addAttribute("msg", user.getClientId());
		status.setComplete();
		return "login/loginFirst";
	}

	@GetMapping(value = "/seeClient/{id}")
	public String seeClient(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Client client = clientService.findOne(id);
		if (client == null) {
			flash.addFlashAttribute("danger", "The Client not exist BD¡");
			return "redirect:/clients";
		}
		model.put("client", client);
		model.put("titul", "Welcome " + client.getSurname());
		return "seeClient";
	}

	@RequestMapping(value = "/formClient/{id}")
	public String editFormClient(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Client client = null;
		if (id > 0) {// si el client es mayor que 0 lo buscamos en la bd
			client = clientService.findOne(id);
			if (client == null) {
				return "redirect:/seeClient";
			}
		} else {
			return "redirect:/seeClient";
		}
		model.put("client", client);
		model.put("titul", "Edit Client");
		return "formClient";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Client client = clientService.findOne(id);
			clientService.delete(id);
			flash.addFlashAttribute("success", "Client delete OK¡¡");

			if (uploadFileService.delete(client.getPhoto())) {
				flash.addFlashAttribute("info", "Photo " + client.getPhoto() + "delete OK");
			}
		}
		return "redirect:/clients";
	}

	/* cargar la imagen desde un stream de salida en la respuesta */
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> seePhoto(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=/" + resource.getFilename() + "/")
				.body(resource);

	}

}// end class
