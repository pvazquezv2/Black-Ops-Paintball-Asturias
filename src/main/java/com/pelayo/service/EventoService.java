package com.pelayo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Evento;
import com.pelayo.repository.EventoRepository;

import jakarta.transaction.Transactional;

@Service
public class EventoService {

	@Autowired 
	private EventoRepository eventoRepo;
	
	public void insertar(Evento evento) {
        eventoRepo.saveAndFlush(evento);
    }

    public List<Evento> verTodos() {
        return eventoRepo.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepo.findById(id);
    }

    public List<Evento> buscarPorFechaHora(LocalDateTime fechaHora) {
        return eventoRepo.findByFechaHora(fechaHora);
    }

    @Transactional
    @Modifying
    public boolean borrarEvento(Long id) {
        if (eventoRepo.existsById(id)) {
            eventoRepo.deleteById(id);
            return true;
        }
        return false;
    }
	
	
}
