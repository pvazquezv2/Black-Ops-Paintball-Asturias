package com.pelayo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva, Long> {
	 
	List<Reserva> findByFechaReserva(LocalDate fechaReserva);

}
