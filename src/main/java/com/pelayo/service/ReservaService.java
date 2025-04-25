package com.pelayo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Reserva;
import com.pelayo.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepo;
	
	public void insertar(Reserva reserva) {
        reservaRepo.saveAndFlush(reserva);
    }

    public List<Reserva> verTodas() {
        return reservaRepo.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepo.findById(id);
    }

    public List<Reserva> buscarPorFecha(LocalDate fechaReserva) {
        return reservaRepo.findByFechaReserva(fechaReserva);
    }
    
    @Transactional
    @Modifying
    public boolean borrarReserva(Long id) {
        if (reservaRepo.existsById(id)) {
            reservaRepo.deleteById(id);
            return true;
        }
        return false;
    }
	
	
	
	
}
