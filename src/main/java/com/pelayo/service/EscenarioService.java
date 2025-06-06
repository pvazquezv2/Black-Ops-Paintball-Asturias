package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.repository.EscenarioRepository;

/**
 * Servicio encargado de manejar la lógica relacionada con los escenarios de
 * juego
 */
@Service
public class EscenarioService {

	@Autowired
	private EscenarioRepository escenarioRepo;

	/**
	 * Devuelve la lista completa de escenarios disponibles
	 */
	public List<Escenario> verTodos() {
		return escenarioRepo.findAll();
	}

	/**
	 * Busca un escenario por su nombre. Si no lo encuentra, devuelve null.
	 */
	public Escenario findByNombre(String nombreEscenario) {
		return escenarioRepo.findByNombre(nombreEscenario).orElse(null);
	}

	/**
	 * Busca un escenario por su ID
	 */
	public Escenario findEscenarioById(Long id) {
		return escenarioRepo.findEscenarioById(id);
	}

}
