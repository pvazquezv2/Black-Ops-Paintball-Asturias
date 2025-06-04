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

@Controller
public class SuscripcionController {

	@Autowired
	private SuscripcionService suscripcionService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EventoService eventoService;

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

	@GetMapping("/admin/listado_suscripciones")
	public String listadoSuscripciones(Model model, Authentication auth) {

		List<Suscripcion> suscripciones = suscripcionService.verTodas();

		model.addAttribute("suscripciones", suscripciones);
		return "admin/listado_suscripciones";
	}

	@GetMapping("/admin/eliminar_suscripcion")
	public String verSuscripcionesParaEliminar(Model model) {
		model.addAttribute("suscripciones", suscripcionService.verTodas());
		return "admin/eliminar_suscripcion";
	}

	@PostMapping("/admin/eliminar_suscripcion/{id}")
	public String eliminarSuscripcion(@PathVariable Long id) {
		suscripcionService.eliminarPorId(id);
		return "redirect:/admin/eliminar_suscripcion";
	}

}
