package com.pelayo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pelayo.model.Escenario;
import com.pelayo.service.EscenarioService;




@Controller
public class EscenarioController {
	
	@Autowired
	private EscenarioService escenarioService;
	
	// Mostrar todos los escenarios
//	@GetMapping
//	public String listarEscenarios(Model model) {
//		List<Escenario> escenarios = escenarioService.verTodos();
//		model.addAttribute("escenarios", escenarios);
//		return "escenarios/lista";
//	}
	
	//formulario para añadir nuevo escenario
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("escenario", new Escenario());
		return "escenarios/formulario";
	}
	
	//guardar escenario enviado desde el formulario
	@PostMapping("/guardar")
	public String guardarEscenario(@ModelAttribute Escenario escenario) {
		escenarioService.insertar(escenario);
		return "redirect:/escenarios";
	}
	
	//eliminar escenario por ID (solo admin deberia de ver esto)
	@GetMapping("/eliminar/{id}")
	public String eliminarEscenario(@PathVariable("id") Long id) {
		//escenarioService.eliminarPorId(id);
		return "redirect:/escenarios";
	}

}
