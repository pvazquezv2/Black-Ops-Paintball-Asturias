package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.repository.EscenarioRepository;

@Service
public class EscenarioService {

	@Autowired
	private EscenarioRepository escenarioRepo;

	public List<Escenario> verTodos() {
		return escenarioRepo.findAll();
	}

	public Escenario findByNombre(String nombreEscenario) {
		return escenarioRepo.findByNombre(nombreEscenario).orElse(null);
	}

	public Escenario findEscenarioById(Long id) {
		return escenarioRepo.findEscenarioById(id);
	}



}
