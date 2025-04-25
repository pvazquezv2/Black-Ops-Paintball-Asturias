package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.repository.EscenarioRepository;

import jakarta.transaction.Transactional;

@Service
public class EscenarioService {
	
	@Autowired
	private EscenarioRepository escenarioRepo;
	
	public void insertar(Escenario escenario) {
        escenarioRepo.saveAndFlush(escenario);
    }

    public List<Escenario> verTodos() {
        return escenarioRepo.findAll();
    }

    public Optional<Escenario> buscarPorId(Long id) {
        return escenarioRepo.findById(id);
    }

    public List<Escenario> buscarPorNombre(String nombre) {
        return escenarioRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional
    @Modifying
    public boolean borrarEscenario(Long id) {
        if (escenarioRepo.existsById(id)) {
            escenarioRepo.deleteById(id);
            return true;
        } 
        return false;
    }

}
