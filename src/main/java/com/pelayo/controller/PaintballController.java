package com.pelayo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pelayo.model.Persona;
import com.pelayo.service.PersonaService;

@Controller
public class PaintballController {
	@Autowired
    private PersonaService personaService;
	
	@GetMapping("/")
	public String mostrarIndex() {
		return "index";
	}

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("persona", new Persona());
        return "login"; // Busca login.html en /templates
    }

    // Procesar el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("persona") Persona personaForm, Model model) {
        Persona persona = personaService.buscarPersonaPorUsuario(personaForm.getNombre());

        if (persona != null && persona.getPassword().equals(personaForm.getPassword())) {
            // Login exitoso
            model.addAttribute("usuario", persona);

            if (persona.getRol().toString().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin"; // Redirige a página de admin
            } else {
                return "redirect:/usuario"; // Redirige a página de usuario normal
            }
        } else {
            // Login fallido
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "login";
        }
    }

    // Página de inicio del usuario normal
    @GetMapping("/usuario")
    public String paginaUsuario() {
        return "usuario"; // usuario.html
    }

    // Página de inicio del administrador
    @GetMapping("/admin")
    public String paginaAdmin() {
        return "admin"; // admin.html
    }
}
