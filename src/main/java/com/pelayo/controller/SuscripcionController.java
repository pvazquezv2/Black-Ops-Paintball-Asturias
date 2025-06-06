package com.pelayo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pelayo.model.Evento;
import com.pelayo.model.Persona;
import com.pelayo.model.Suscripcion;
import com.pelayo.service.EventoService;
import com.pelayo.service.PersonaService;
import com.pelayo.service.SuscripcionService;

/**
 * Controlador encargado de gestionar las suscripciones de usuarios a eventos,
 * incluyendo suscripción individual y administración de todas las
 * suscripciones.
 */
@Controller
public class SuscripcionController {

	@Autowired
	private SuscripcionService suscripcionService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EventoService eventoService;

	/**
	 * Permite a un usuario autenticado suscribirse a un evento. Valida si ya está
	 * suscrito previamente o si el evento existe.
	 * 
	 * @param eventoId  ID del evento al que se desea suscribir
	 * @param principal datos del usuario autenticado
	 * @return redirección con mensaje o error
	 */
	@PostMapping("/suscribirse")
	public String suscribirse(@RequestParam("eventoId") Long eventoId, Principal principal) {

		if (principal == null) {
			return "redirect:/login";
		}

		String username = principal.getName();
		Optional<Persona> personaOpt = personaService.buscarPersonaPorUsuario(username);

		if (personaOpt.isEmpty()) {
			return "redirect:/login?error";
		}

		Optional<Evento> eventoOpt = eventoService.buscarPorId(eventoId);
		if (eventoOpt.isEmpty()) {
			return "redirect:/eventos?error=EventoNoEncontrado";
		}

		Optional<Suscripcion> suscripcionExistente = suscripcionService
				.buscarPorPersonaYEvento(personaOpt.get().getId(), eventoId);
		if (suscripcionExistente.isPresent()) {
			return "redirect:/eventos?error=Ya te has suscrito a este evento";
		}

		Suscripcion suscripcion = new Suscripcion(LocalDate.now());
		suscripcion.setPersona(personaOpt.get());
		suscripcion.setEvento(eventoOpt.get());

		suscripcionService.insertar(suscripcion);

		return "redirect:/eventos?mensaje=Te has suscrito a este evento";
	}

	/**
	 * Muestra un listado completo de suscripciones para el panel de administración.
	 * 
	 * @param model modelo para pasar los datos a la vista
	 * @param auth  autenticación del usuario administrador
	 * @return vista con la lista de suscripciones
	 */
	@GetMapping("/admin/listado_suscripciones")
	public String listadoSuscripciones(Model model, Authentication auth) {

		List<Suscripcion> suscripciones = suscripcionService.verTodas();

		model.addAttribute("suscripciones", suscripciones);
		return "admin/listado_suscripciones";
	}

	/**
	 * Muestra las suscripciones disponibles para ser eliminadas desde el panel
	 * admin.
	 * 
	 * @param model modelo para la vista
	 * @return vista con suscripciones y opción de eliminación
	 */
	@GetMapping("/admin/eliminar_suscripcion")
	public String verSuscripcionesParaEliminar(Model model) {
		model.addAttribute("suscripciones", suscripcionService.verTodas());
		return "admin/eliminar_suscripcion";
	}

	/**
	 * Elimina una suscripción específica por su ID desde el panel de administrador.
	 * 
	 * @param id ID de la suscripción a eliminar
	 * @return redirección a la vista de eliminación tras la acción
	 */
	@PostMapping("/admin/eliminar_suscripcion/{id}")
	public String eliminarSuscripcion(@PathVariable Long id) {
		suscripcionService.eliminarPorId(id);
		return "redirect:/admin/eliminar_suscripcion";
	}

}
