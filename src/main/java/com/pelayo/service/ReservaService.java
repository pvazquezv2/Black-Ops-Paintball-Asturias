package com.pelayo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Escenario;
import com.pelayo.model.Evento;
import com.pelayo.model.Persona;
import com.pelayo.model.Reserva;
import com.pelayo.repository.EventoRepository;
import com.pelayo.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepo;

	@Autowired
	EventoRepository eventoRepo;

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

	public void guardar(Reserva reserva) {
		reservaRepo.save(reserva);
	}

	public void eliminarPorId(Long id) {
		reservaRepo.deleteById(id);
	}

	public boolean existeReserva(LocalDateTime fechaHora, Escenario escenario, String modoJuego) {
		return reservaRepo.existsByFechaReservaAndEscenarioAndModoJuego(fechaHora, escenario, modoJuego);
	}

	public boolean existeConflictoReservaConEventoOReserva(LocalDateTime fechaReserva, Escenario escenario) {
		LocalDate fecha = fechaReserva.toLocalDate();
		LocalTime horaReserva = fechaReserva.toLocalTime();

		List<Evento> eventos = eventoRepo.findByEscenarioAndFecha(escenario, fecha);

		for (Evento evento : eventos) {

			if (!horaReserva.isBefore(evento.getHoraInicio()) && horaReserva.isBefore(evento.getHoraFin())) {
				return true;
			}
		}

		LocalDateTime startOfDay = fecha.atStartOfDay();
		LocalDateTime endOfDay = fecha.plusDays(1).atStartOfDay();

		List<Reserva> reservas = reservaRepo.findByEscenarioAndFechaReservaBetween(escenario, startOfDay, endOfDay);

		for (Reserva reserva : reservas) {

			if (reserva.getFechaReserva().equals(fechaReserva)) {
				return true;
			}
		}

		return false;
	}

	public List<Reserva> buscarPorPersona(Persona persona) {
		return reservaRepo.findByPersona(persona);
	}

	public List<Reserva> findByEscenarioId(Long escenarioId) {
		return reservaRepo.findByEscenarioId(escenarioId);
	}

	public List<Reserva> findByPersonaId(Long personaId) {
		return reservaRepo.findByPersonaId(personaId);
	}

	public List<Reserva> findByFechaReservaBetween(LocalDateTime inicioDT, LocalDateTime finDT) {
		return reservaRepo.findByFechaReservaBetween(inicioDT, finDT);
	}

	public Reserva findById(Long id) {
		return reservaRepo.findById(id).orElse(null);
	}

}
