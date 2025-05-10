package com.pelayo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pelayo.model.Suscripcion;
import com.pelayo.service.SuscripcionService;

@Controller
public class SuscripcionController {
	@Autowired
    private SuscripcionService suscripcionService;

    @PostMapping("/insertar")
    public void insertar(@RequestBody Suscripcion suscripcion) {
        suscripcionService.insertar(suscripcion);
    }

    @GetMapping("/listar")
    public List<Suscripcion> listar() {
        return suscripcionService.verTodas();
    }

    @GetMapping("/buscarPorPersona/{personaId}")
    public List<Suscripcion> buscarPorPersona(@PathVariable Long personaId) {
        return suscripcionService.buscarPorPersonaId(personaId);
    }

    @DeleteMapping("/borrar/{id}")
    public boolean borrar(@PathVariable Long id) {
        return suscripcionService.borrarSuscripcion(id);
    }
}
