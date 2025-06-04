package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Persona;
import com.pelayo.model.Suscripcion;
import com.pelayo.repository.SuscripcionRepository;

@Service
public class SuscripcionService {

	@Autowired
	private SuscripcionRepository suscripcionRepo;

	public void insertar(Suscripcion suscripcion) {
		suscripcionRepo.saveAndFlush(suscripcion);
	}

	public List<Suscripcion> verTodas() {
		return suscripcionRepo.findAll();
	}

	public Optional<Suscripcion> buscarPorId(Long id) {
		return suscripcionRepo.findById(id);
	}

	public List<Suscripcion> buscarPorPersonaId(Long personaId) {
		return suscripcionRepo.findByPersonaId(personaId);
	}

	public Optional<Suscripcion> buscarPorPersonaYEvento(Long personaId, Long eventoId) {
		return suscripcionRepo.findByPersonaIdAndEventoId(personaId, eventoId);
	}

	public List<Suscripcion> buscarPorPersona(Persona persona) {
		return suscripcionRepo.findByPersona(persona);
	}

	public void guardar(Suscripcion suscripcion) {
		suscripcionRepo.save(suscripcion);
	}

	public void eliminarPorId(Long id) {
		suscripcionRepo.deleteById(id);
	}
}
