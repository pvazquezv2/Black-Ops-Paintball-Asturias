package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Suscripcion;
import com.pelayo.repository.SuscripcionRepository;

import jakarta.transaction.Transactional;

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

    @Transactional
    @Modifying
    public boolean borrarSuscripcion(Long id) {
        if (suscripcionRepo.existsById(id)) {
            suscripcionRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
