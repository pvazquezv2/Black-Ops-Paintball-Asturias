package com.pelayo.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.model.Evento;
import com.pelayo.repository.EventoRepository;

/**
 * Servicio encargado de la lógica relacionada con los eventos. Aquí se
 * gestionan búsquedas, guardado y eliminación de eventos.
 */
@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepo;

	/**
	 * Devuelve la lista completa de eventos registrados.
	 */
	public List<Evento> findAll() {
		return eventoRepo.findAll();
	}

	/**
	 * Busca un evento por su ID y lo devuelve envuelto en Optional. Si no lo
	 * encuentra, devuelve un Optional vacío.
	 */
	public Optional<Evento> buscarPorId(Long id) {
		Evento evento = eventoRepo.findById(id).orElse(null);
		return Optional.ofNullable(evento);
	}

	/**
	 * Busca todos los eventos que coincidan con un escenario y una fecha
	 * específica.
	 */
	public List<Evento> findByEscenarioAndFecha(Escenario escenario, LocalDate fecha) {
		return eventoRepo.findByEscenarioAndFecha(escenario, fecha);
	}

	/**
	 * Devuelve todos los eventos (igual que findAll, puede usarse para separar
	 * lógica si se requiere en futuro).
	 */
	public List<Evento> verTodos() {
		return eventoRepo.findAll();
	}

	/**
	 * Guarda un evento nuevo o actualiza uno existente.
	 */
	public void guardar(Evento evento) {
		eventoRepo.save(evento);
	}

	/**
	 * Elimina un evento por su ID.
	 */
	public void eliminarPorId(Long id) {
		eventoRepo.deleteById(id);
	}

}
