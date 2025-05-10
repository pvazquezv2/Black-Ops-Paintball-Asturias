package com.pelayo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pelayo.model.Evento;
import com.pelayo.service.EventoService;

@Controller
public class EventoController {
	@Autowired
    private EventoService eventoService;

    @PostMapping("/insertar")
    public void insertar(@RequestBody Evento evento) {
        eventoService.insertar(evento);
    }

    @GetMapping("/listar")
    public List<Evento> listar() {
        return eventoService.verTodos();
    }

    @GetMapping("/buscarPorFechaHora")
    public List<Evento> buscarPorFechaHora(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        return eventoService.buscarPorFechaHora(fechaHora);
    }

    @DeleteMapping("/borrar/{id}")
    public boolean borrar(@PathVariable Long id) {
        return eventoService.borrarEvento(id);
    }
}
