package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Persona;

import com.pelayo.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepo;

	public void insertar(Persona p) {
		personaRepo.saveAndFlush(p);
	}

	public List<Persona> verTodas() {
		return personaRepo.findAll();
	}

	public Optional<Persona> buscarPorId(Long id) {
		return personaRepo.findById(id);
	}

	public Optional<Persona> buscarPorEmail(String email) {
		return personaRepo.findByEmail(email);
	}

	public Optional<Persona> buscarPorNombreUsuario(String nombreUsuario) {
		return personaRepo.findByNombreUsuario(nombreUsuario);
	}

	public boolean emailExiste(String email) {
		return personaRepo.existsByEmail(email);
	}

	public boolean nombreUsuarioExiste(String nombreUsuario) {
		return personaRepo.existsByNombreUsuario(nombreUsuario);
	}

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

	public Optional<Persona> buscarPorId(long idUsuario) {
		return personaRepo.findById(idUsuario);
	}

	public Optional<Persona> buscarPersonaPorUsuario(String usuario) {
		Optional<Persona> persona = personaRepo.findByNombreUsuario(usuario);
		return persona;
	}

	public void guardar(Persona persona) {
		personaRepo.save(persona);
	}

	public void eliminarPorId(Long id) {
		personaRepo.deleteById(id);
	}

}
