package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Persona;

import com.pelayo.repository.PersonaRepository;

/**
 * Servicio encargado de la gestión de operaciones relacionadas con entidades
 * {@link Persona}.
 */
@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepo;

	/**
	 * Guarda una nueva persona en la base de datos.
	 *
	 * @param p la persona a insertar
	 */
	public void insertar(Persona p) {
		personaRepo.saveAndFlush(p);
	}

	/**
	 * Obtiene una lista con todas las personas registradas.
	 *
	 * @return lista de personas
	 */
	public List<Persona> verTodas() {
		return personaRepo.findAll();
	}

	/**
	 * Busca una persona por su ID.
	 *
	 * @param id identificador de la persona
	 * @return un {@link Optional} que puede contener la persona encontrada
	 */
	public Optional<Persona> buscarPorId(Long id) {
		return personaRepo.findById(id);
	}

	/**
	 * Busca una persona por su email.
	 *
	 * @param email correo electrónico
	 * @return un {@link Optional} que puede contener la persona encontrada
	 */
	public Optional<Persona> buscarPorEmail(String email) {
		return personaRepo.findByEmail(email);
	}

	/**
	 * Busca una persona por su nombre de usuario.
	 *
	 * @param nombreUsuario nombre de usuario
	 * @return un {@link Optional} que puede contener la persona encontrada
	 */
	public Optional<Persona> buscarPorNombreUsuario(String nombreUsuario) {
		return personaRepo.findByNombreUsuario(nombreUsuario);
	}

	/**
	 * Verifica si un email ya está registrado.
	 *
	 * @param email correo electrónico
	 * @return true si el email existe, false en caso contrario
	 */
	public boolean emailExiste(String email) {
		return personaRepo.existsByEmail(email);
	}

	/**
	 * Verifica si un nombre de usuario ya está registrado.
	 *
	 * @param nombreUsuario nombre de usuario
	 * @return true si el nombre de usuario existe, false en caso contrario
	 */
	public boolean nombreUsuarioExiste(String nombreUsuario) {
		return personaRepo.existsByNombreUsuario(nombreUsuario);
	}

	/**
	 * Valida los datos básicos de una persona.
	 *
	 * @param pers la persona a validar
	 * @return true si los datos son válidos, false en caso contrario
	 */
	public boolean validarPersona(Persona pers) {
		if (pers == null) {
			return false;
		}
		if (pers.getNombre() == null || pers.getNombre().isEmpty()) {
			return false;
		}
		if (pers.getNombre().length() < 3 || pers.getNombre().length() > 20) {
			return false;
		}
		if (pers.getEmail() == null || pers.getEmail().isEmpty()) {
			return false;
		}
		if (pers.getEmail().length() < 5 || !pers.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
				|| pers.getEmail().length() > 40) {
			return false;
		}
		return true;
	}

	/**
	 * Busca una persona por su ID (tipo primitivo).
	 *
	 * @param idUsuario identificador del usuario
	 * @return un {@link Optional} que puede contener la persona
	 */
	public Optional<Persona> buscarPorId(long idUsuario) {
		return personaRepo.findById(idUsuario);
	}

	/**
	 * Busca una persona por su nombre de usuario.
	 *
	 * @param usuario nombre de usuario
	 * @return un {@link Optional} con la persona encontrada
	 */
	public Optional<Persona> buscarPersonaPorUsuario(String usuario) {
		Optional<Persona> persona = personaRepo.findByNombreUsuario(usuario);
		return persona;
	}

	/**
	 * Guarda o actualiza una persona en la base de datos.
	 *
	 * @param persona la persona a guardar
	 */
	public void guardar(Persona persona) {
		personaRepo.save(persona);
	}

	/**
	 * Elimina una persona por su ID.
	 *
	 * @param id identificador de la persona
	 */
	public void eliminarPorId(Long id) {
		personaRepo.deleteById(id);
	}

}
