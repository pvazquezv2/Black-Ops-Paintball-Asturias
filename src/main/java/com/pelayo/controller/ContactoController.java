package com.pelayo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pelayo.model.Contacto;

import com.pelayo.service.ContactoService;

@Controller
public class ContactoController {
	@Autowired
	private ContactoService contactoService;

	@PostMapping("/contacto/enviar")
	public String insertarDesdeFormulario(Contacto contacto, Model model) {
		contacto.setFechaContacto(LocalDateTime.now());
		contactoService.insertar(contacto);

		model.addAttribute("mensaje", "Mensaje enviado correctamente!");
		return "contacto";
	}

	@GetMapping("/admin/listado_mensajes")
	public String listarMensajes(Model model) {
		List<Contacto> mensajes = contactoService.verTodos();

		model.addAttribute("contactos", mensajes);
		return "admin/listado_mensajes";
	}

	@GetMapping("/admin/eliminar_mensaje")
	public String listarMensajesparaEliminar(Model model) {
		List<Contacto> mensajes = contactoService.verTodos();

		model.addAttribute("contactos", mensajes);
		return "admin/eliminar_mensaje";
	}

	@PostMapping("/admin/eliminar_mensaje/{id}")
	public String eliminarMensaje(@PathVariable Long id) {
		contactoService.eliminarPorId(id);
		return "redirect:/admin/eliminar_mensaje";
	}

}
