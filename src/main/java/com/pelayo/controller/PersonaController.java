package com.pelayo.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pelayo.model.Persona;
import com.pelayo.service.PersonaService;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/insertar")
	public void insertar(@RequestBody Persona persona) {
		personaService.insertar(persona);
	}
	
	@GetMapping("/listar")
	public Collection<Persona> verTodos(){
		return personaService.verTodas();
	}
	
	@GetMapping("/buscar/{id}")
	public Optional<Persona> buscarPorId(@PathVariable Long id){
		return personaService.buscarPorId(id);
	}
	
	@DeleteMapping("borrar/{id}")
	public boolean borrar(@PathVariable Long id) {
		return personaService.borrarPersona(id);
	}
}
