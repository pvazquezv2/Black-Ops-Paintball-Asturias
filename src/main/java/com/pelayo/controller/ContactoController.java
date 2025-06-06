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

/**
 * Controlador que gestiona los mensajes enviados desde el formulario de
 * contacto y su administración desde el panel del admin.
 */
@Controller
public class ContactoController {
	@Autowired
	private ContactoService contactoService;

	/**
	 * Guarda el mensaje enviado desde el formulario de contacto. Le pone la fecha
	 * actual y muestra un mensaje de confirmación.
	 */
	@PostMapping("/contacto/enviar")
	public String insertarDesdeFormulario(Contacto contacto, Model model) {
		contacto.setFechaContacto(LocalDateTime.now());
		contactoService.insertar(contacto);

		model.addAttribute("mensaje", "Mensaje enviado correctamente!");
		return "contacto";
	}

	/**
	 * Muestra todos los mensajes recibidos, para que el admin los pueda ver.
	 */
	@GetMapping("/admin/listado_mensajes")
	public String listarMensajes(Model model) {
		List<Contacto> mensajes = contactoService.verTodos();

		model.addAttribute("contactos", mensajes);
		return "admin/listado_mensajes";
	}

	/**
	 * Muestra los mensajes con opción de eliminarlos.
	 */
	@GetMapping("/admin/eliminar_mensaje")
	public String listarMensajesparaEliminar(Model model) {
		List<Contacto> mensajes = contactoService.verTodos();

		model.addAttribute("contactos", mensajes);
		return "admin/eliminar_mensaje";
	}

	/**
	 * Elimina un mensaje según su ID (cuando el admin lo decide).
	 */
	@PostMapping("/admin/eliminar_mensaje/{id}")
	public String eliminarMensaje(@PathVariable Long id) {
		contactoService.eliminarPorId(id);
		return "redirect:/admin/eliminar_mensaje";
	}

}
