package com.pelayo.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pelayo.model.Evento;
import com.pelayo.model.Persona;
import com.pelayo.model.Suscripcion;
import com.pelayo.service.EscenarioService;
import com.pelayo.service.EventoService;
import com.pelayo.service.PersonaService;
import com.pelayo.service.SuscripcionService;

@Controller
public class EventoController {
	@Autowired
	private EventoService eventoService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private SuscripcionService suscripcionService;

	@Autowired
	private EscenarioService escenarioService;

	@GetMapping("/eventos")
	public String listarEventos(@RequestParam(value = "mensaje", required = false) String mensaje,
			@RequestParam(value = "error", required = false) String error, Model model) {
		List<Evento> eventos = eventoService.findAll();
		model.addAttribute("eventos", eventos);

		if (mensaje != null) {
			model.addAttribute("mensaje", mensaje);
		}

		if (error != null) {
			model.addAttribute("error", error);
		}

		return "eventos";
	}

	@GetMapping("/misEventos")
	public String verMisEventos(Model model, Authentication auth) {
		Optional<Persona> personaOpt = personaService.buscarPorNombreUsuario(auth.getName());

		if (personaOpt.isEmpty()) {
			return "redirect:/login";
		}

		List<Suscripcion> suscripciones = suscripcionService.buscarPorPersona(personaOpt.get());
		List<Evento> eventos = suscripciones.stream().map(Suscripcion::getEvento).toList();

		model.addAttribute("eventos", eventos);
		return "misEventos";
	}

	@GetMapping("/admin/listado_eventos")
	public String listado_eventos(Model model, Authentication auth) {

		List<Evento> eventos = eventoService.verTodos();

		model.addAttribute("eventos", eventos);
		return "admin/listado_eventos";
	}

	@GetMapping("/admin/eliminar_evento")
	public String verEventosParaEliminar(Model model) {
		model.addAttribute("eventos", eventoService.verTodos());
		return "admin/eliminar_evento";
	}

	@PostMapping("/admin/eliminar_evento/{id}")
	public String eliminarEvento(@PathVariable Long id) {
		eventoService.eliminarPorId(id);
		return "redirect:/admin/eliminar_evento";
	}

	@GetMapping("/admin/nuevo_evento")
	public String mostrarFormularioNuevoEvento(Model model) {
		model.addAttribute("evento", new Evento());
		model.addAttribute("escenarios", escenarioService.verTodos());
		return "admin/nuevo_evento";
	}

	@PostMapping("/admin/nuevo_evento")
	public String guardarEvento(@ModelAttribute Evento evento, @RequestParam("imagen") MultipartFile imagen,
	                            RedirectAttributes redirectAttributes) {
	    try {
	        if (!imagen.isEmpty()) {
	            String nombreArchivo = Paths.get(imagen.getOriginalFilename()).getFileName().toString();
	            // No se copia el archivo, solo se usa la ruta existente
	            evento.setImagenUrl("/img/" + nombreArchivo);
	        }

	        eventoService.guardar(evento);
	        redirectAttributes.addFlashAttribute("mensaje", "Evento creado correctamente.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "Error al crear el evento.");
	    }

	    return "redirect:/admin/nuevo_evento";
	}




}
