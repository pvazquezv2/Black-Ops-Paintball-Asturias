package com.pelayo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pelayo.model.Persona;
import java.security.Principal;

import com.pelayo.service.PersonaService;

@Controller
public class PaintballController {

	@Autowired
	private PersonaService personaService;

	@GetMapping("/")
	public String mostrarIndex() {
		return "index";
	}

	@GetMapping("/escenarios")
	public String escenarios() {
		return "escenarios";
	}

	@GetMapping("/modosDeJuego")
	public String modosDeJuego() {
		return "modosDeJuego";
	}

	@GetMapping("/equipamiento")
	public String equipamiento() {
		return "equipamiento";
	}

	@GetMapping("/quienesSomos")
	public String quienesSomos() {
		return "quienesSomos";
	}

	@GetMapping("/reglamento")
	public String reglamento() {
		return "reglamento";
	}

	@GetMapping("/preguntasFrecuentes")
	public String preguntasFrecuentes() {
		return "preguntasFrecuentes";
	}

	@GetMapping("/contacto")
	public String contacto() {
		return "contacto";
	}

	@GetMapping("/reservar")
	public String reservar(Model model, Principal principal) {
		if (principal != null) {
			String nombreUsuario = principal.getName();
			Optional<Persona> personaOpt = personaService.buscarPersonaPorUsuario(nombreUsuario);
			if (personaOpt.isPresent()) {
				model.addAttribute("usuario", personaOpt.get());
			} else {
				return "redirect:/login?error";
			}
		} else {

			return "redirect:/login";
		}
		return "reservar";
	}

	@GetMapping("/usuario")
	public String paginaUsuario() {
		return "/";
	}

	@GetMapping("/admin")
	public String paginaAdmin() {
		return "admin/admin";
	}

	@GetMapping("/admin/gestion_eventos")
	public String gestionEventos() {
		return "admin/gestion_eventos";
	}

	@GetMapping("/admin/gestion_personas")
	public String gestionPersonas() {
		return "admin/gestion_personas";
	}

	@GetMapping("/admin/gestion_mensajes")
	public String gestionMensajes() {
		return "admin/gestion_mensajes";
	}

	@GetMapping("/admin/gestion_precios")
	public String gestionPrecios() {
		return "admin/gestion_precios";
	}

	@GetMapping("/admin/gestion_reservas")
	public String gestionReservas() {
		return "admin/gestion_reservas";
	}

	@GetMapping("/admin/gestion_suscripciones")
	public String gestionSuscipciones() {
		return "admin/gestion_suscripciones";
	}

}
