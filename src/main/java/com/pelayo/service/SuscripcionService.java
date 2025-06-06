package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Persona;
import com.pelayo.model.Suscripcion;
import com.pelayo.repository.SuscripcionRepository;

/**
 * Servicio encargado de la gestión de suscripciones de usuarios a eventos.
 * Permite insertar, buscar, listar y eliminar suscripciones.
 */
@Service
public class SuscripcionService {

	@Autowired
	private SuscripcionRepository suscripcionRepo;

	/**
	 * Inserta una nueva suscripción y la guarda de inmediato en la base de datos.
	 *
	 * @param suscripcion la suscripción a insertar
	 */
	public void insertar(Suscripcion suscripcion) {
		suscripcionRepo.saveAndFlush(suscripcion);
	}

	/**
	 * Devuelve una lista con todas las suscripciones registradas.
	 *
	 * @return lista de suscripciones
	 */
	public List<Suscripcion> verTodas() {
		return suscripcionRepo.findAll();
	}

	/**
	 * Busca una suscripción por su identificador único.
	 *
	 * @param id identificador de la suscripción
	 * @return un {@link Optional} que puede contener la suscripción encontrada
	 */
	public Optional<Suscripcion> buscarPorId(Long id) {
		return suscripcionRepo.findById(id);
	}

	/**
	 * Busca todas las suscripciones asociadas a una persona por su ID.
	 *
	 * @param personaId identificador de la persona
	 * @return lista de suscripciones de la persona
	 */
	public List<Suscripcion> buscarPorPersonaId(Long personaId) {
		return suscripcionRepo.findByPersonaId(personaId);
	}

	/**
	 * Busca una suscripción específica por ID de persona e ID de evento.
	 *
	 * @param personaId identificador de la persona
	 * @param eventoId  identificador del evento
	 * @return un {@link Optional} que puede contener la suscripción encontrada
	 */
	public Optional<Suscripcion> buscarPorPersonaYEvento(Long personaId, Long eventoId) {
		return suscripcionRepo.findByPersonaIdAndEventoId(personaId, eventoId);
	}

	/**
	 * Busca todas las suscripciones asociadas a una persona específica.
	 *
	 * @param persona objeto {@link Persona}
	 * @return lista de suscripciones de esa persona
	 */
	public List<Suscripcion> buscarPorPersona(Persona persona) {
		return suscripcionRepo.findByPersona(persona);
	}

	/**
	 * Guarda o actualiza una suscripción en la base de datos.
	 *
	 * @param suscripcion la suscripción a guardar
	 */
	public void guardar(Suscripcion suscripcion) {
		suscripcionRepo.save(suscripcion);
	}

	/**
	 * Elimina una suscripción por su ID.
	 *
	 * @param id identificador de la suscripción a eliminar
	 */
	public void eliminarPorId(Long id) {
		suscripcionRepo.deleteById(id);
	}
}
