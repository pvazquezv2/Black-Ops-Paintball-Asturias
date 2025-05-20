package com.pelayo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pelayo.model.Contacto;
import com.pelayo.service.ContactoService;

@RestController
@RequestMapping("/contactos")
public class ContactoController {
	@Autowired
    private ContactoService contactoService;

    @PostMapping("/enviar")
    public void insertar(@RequestBody Contacto contacto) {
        contactoService.insertar(contacto);
    }

    @GetMapping("/listar")
    public List<Contacto> listar() {
        return contactoService.verTodos();
    }

    @GetMapping("/buscarPorPersona/{personaId}")
    public Optional<Contacto> buscarPorPersona(@PathVariable Long personaId) {
        return contactoService.buscarPorId(personaId);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
		return null;
        // lógica
    }
}
