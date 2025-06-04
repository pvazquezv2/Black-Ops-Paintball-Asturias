package com.pelayo.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.model.Evento;
import com.pelayo.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepo;

	public List<Evento> findAll() {
		return eventoRepo.findAll();
	}

	public Optional<Evento> buscarPorId(Long id) {
		Evento evento = eventoRepo.findById(id).orElse(null);
		return Optional.ofNullable(evento);
	}

	public List<Evento> findByEscenarioAndFecha(Escenario escenario, LocalDate fecha) {
		return eventoRepo.findByEscenarioAndFecha(escenario, fecha);
	}

	public List<Evento> verTodos() {
		return eventoRepo.findAll();
	}

	public void guardar(Evento evento) {
		eventoRepo.save(evento);
	}

	public void eliminarPorId(Long id) {
		eventoRepo.deleteById(id);
	}

}
