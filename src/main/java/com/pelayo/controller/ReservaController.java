package com.pelayo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pelayo.model.Reserva;
import com.pelayo.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@PostMapping("/insertar")
	public void insertar(@RequestBody Reserva reserva) {
		reservaService.insertar(reserva);
	}
	
	@GetMapping("/listar")
	public List<Reserva> listar(){
		return reservaService.verTodas();
	}
	
	@GetMapping("/buscarPorFecha")
    public List<Reserva> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return reservaService.buscarPorFecha(fecha);
    }

    @GetMapping("/buscarPorPersona/{personaId}")
    public Optional<Reserva> buscarPorPersona(@PathVariable Long personaId) {
        return reservaService.buscarPorId(personaId);
    }

    @DeleteMapping("/borrar/{id}") // -> DELETE /reservas/borrar/{id}
    public ResponseEntity<?> borrar(@PathVariable Long id) {
		return null; }
	
	
}
