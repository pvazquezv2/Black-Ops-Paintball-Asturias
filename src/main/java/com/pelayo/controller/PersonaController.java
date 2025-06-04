package com.pelayo.controller;

import com.pelayo.model.Persona;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.pelayo.model.Rol;
import com.pelayo.service.PersonaService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonaController {
	


	@Autowired
	private PersonaService personaService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("errorLogin", "Nombre de usuario o contraseña incorrectos");
		}
		return "login";
	}

	@GetMapping("/redirect")
	public String redirectByRole(Authentication authentication, Model model) {
		if (authentication == null || authentication.getAuthorities() == null) {
			return "redirect:/login";
		}

		for (GrantedAuthority authority : authentication.getAuthorities()) {
			switch (authority.getAuthority()) {
			case "ROLE_ADMIN":
				return "redirect:/admin";
			case "ROLE_USUARIO":
			default:
				return "redirect:/";
			}
		}

		return "redirect:/login";
	}

	@GetMapping("/registro")
	public String registroForm(Model model) {
		model.addAttribute("persona", new Persona());
		return "registro";
	}

	@PostMapping("/registro")
	public String registrarPersona(@ModelAttribute("persona") Persona persona, @RequestParam String password,
			@RequestParam String confirmPassword, Model model) {
		if (!password.equals(confirmPassword)) {
			model.addAttribute("errorRegistro", "Las contraseñas no coinciden.");
			return "registro";
		}

		if (personaService.buscarPorNombreUsuario(persona.getNombreUsuario()).isPresent()) {
			model.addAttribute("errorRegistro", "El nombre de usuario ya existe.");
			return "registro";
		}

		if (personaService.buscarPorEmail(persona.getEmail()).isPresent()) {
			model.addAttribute("errorRegistro", "El email ya está registrado.");
			return "registro";
		}

		persona.setPassword(passwordEncoder.encode(password));
		persona.setRol(Rol.USUARIO);
		personaService.guardar(persona);

		model.addAttribute("msgRegistro", "Usuario registrado correctamente. Ya puedes iniciar sesión.");
		model.addAttribute("persona", new Persona());
		return "registro";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/admin/listado_personas")
	public String listarPersonas(Model model) {
		List<Persona> personas = personaService.verTodas();
		System.out.println("PERSONAS ENCONTRADAS: " + personas.size());
		model.addAttribute("personas", personas);
		return "admin/listado_personas";
	}

	@GetMapping("/admin/nueva_persona")
	public String nuevaPersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "admin/nueva_persona";
	}

	@PostMapping("/admin/nueva_persona")
	public String guardarPersona(@ModelAttribute("persona") Persona persona, @RequestParam String password,
			@RequestParam String confirmPassword, @RequestParam String rol, Model model) {

		if (!password.equals(confirmPassword)) {
			model.addAttribute("errorRegistro", "Las contraseñas no coinciden.");
			return "admin/nueva_persona";
		}

		if (personaService.buscarPorNombreUsuario(persona.getNombreUsuario()).isPresent()) {
			model.addAttribute("errorRegistro", "El nombre de usuario ya existe.");
			return "admin/nueva_persona";
		}

		if (personaService.buscarPorEmail(persona.getEmail()).isPresent()) {
			model.addAttribute("errorRegistro", "El email ya está registrado.");
			return "admin/nueva_persona";
		}

		persona.setPassword(passwordEncoder.encode(password));

		try {
			persona.setRol(Rol.valueOf(rol));
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorRegistro", "Rol inválido.");
			return "admin/nueva_persona";
		}

		personaService.guardar(persona);

		model.addAttribute("msgRegistro", "Persona añadida correctamente.");
		model.addAttribute("persona", new Persona());
		return "admin/nueva_persona";
	}

	@GetMapping("/admin/eliminar_persona")
	public String verPersonasParaEliminar(Model model) {
		model.addAttribute("personas", personaService.verTodas());
		return "admin/eliminar_persona";
	}

	@PostMapping("/admin/eliminar_persona/{id}")
	public String eliminarPersona(@PathVariable Long id) {
		personaService.eliminarPorId(id);
		return "redirect:/admin/eliminar_persona";
	}

}
