package com.pelayo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Escenario;
import com.pelayo.model.Persona;
import com.pelayo.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByFechaReserva(LocalDate fechaReserva);

	boolean existsByFechaReservaAndEscenarioAndModoJuego(LocalDateTime fechaReserva, Escenario escenario,
			String modoJuego);

	List<Reserva> findByEscenarioAndFechaReservaBetween(Escenario escenario, LocalDateTime start, LocalDateTime end);

	List<Reserva> findByPersona(Persona persona);

	List<Reserva> findByEscenarioId(Long escenarioId);

	List<Reserva> findByPersonaId(Long personaId);

	List<Reserva> findByFechaReservaBetween(LocalDateTime inicio, LocalDateTime fin);

}
