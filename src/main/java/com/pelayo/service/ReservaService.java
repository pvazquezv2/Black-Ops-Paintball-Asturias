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

/**
 * Servicio para la gestión de reservas en el sistema. Proporciona operaciones
 * para insertar, consultar, validar conflictos y eliminar reservas.
 */
@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepo;

	@Autowired
	EventoRepository eventoRepo;

	/**
	 * Inserta una nueva reserva en la base de datos y la guarda de inmediato.
	 *
	 * @param reserva la reserva a insertar
	 */
	public void insertar(Reserva reserva) {
		reservaRepo.saveAndFlush(reserva);
	}

	/**
	 * Retorna todas las reservas existentes.
	 *
	 * @return lista de todas las reservas
	 */
	public List<Reserva> verTodas() {
		return reservaRepo.findAll();
	}

	/**
	 * Busca una reserva por su ID.
	 *
	 * @param id identificador de la reserva
	 * @return un {@link Optional} que puede contener la reserva encontrada
	 */
	public Optional<Reserva> buscarPorId(Long id) {
		return reservaRepo.findById(id);
	}

	/**
	 * Busca reservas que coincidan con una fecha específica.
	 *
	 * @param fechaReserva la fecha a buscar
	 * @return lista de reservas para esa fecha
	 */
	public List<Reserva> buscarPorFecha(LocalDate fechaReserva) {
		return reservaRepo.findByFechaReserva(fechaReserva);
	}

	/**
	 * Guarda o actualiza una reserva en la base de datos.
	 *
	 * @param reserva la reserva a guardar
	 */
	public void guardar(Reserva reserva) {
		reservaRepo.save(reserva);
	}

	/**
	 * Elimina una reserva por su ID.
	 *
	 * @param id identificador de la reserva a eliminar
	 */
	public void eliminarPorId(Long id) {
		reservaRepo.deleteById(id);
	}

	/**
	 * Verifica si ya existe una reserva para el mismo escenario, modo de juego y
	 * fecha/hora.
	 *
	 * @param fechaHora fecha y hora de la reserva
	 * @param escenario escenario donde se desea reservar
	 * @param modoJuego modo de juego solicitado
	 * @return true si ya existe una reserva, false en caso contrario
	 */
	public boolean existeReserva(LocalDateTime fechaHora, Escenario escenario, String modoJuego) {
		return reservaRepo.existsByFechaReservaAndEscenarioAndModoJuego(fechaHora, escenario, modoJuego);
	}

	/**
	 * Verifica si existe conflicto de horario con otro evento o reserva en el mismo
	 * escenario y fecha.
	 *
	 * @param fechaReserva fecha y hora de la nueva reserva
	 * @param escenario    escenario donde se desea reservar
	 * @return true si hay conflicto, false si no
	 */
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

	/**
	 * Busca todas las reservas realizadas por una persona específica.
	 *
	 * @param persona la persona que hizo la reserva
	 * @return lista de reservas de esa persona
	 */
	public List<Reserva> buscarPorPersona(Persona persona) {
		return reservaRepo.findByPersona(persona);
	}

	/**
	 * Obtiene las reservas hechas para un escenario dado por ID.
	 *
	 * @param escenarioId identificador del escenario
	 * @return lista de reservas para el escenario
	 */
	public List<Reserva> findByEscenarioId(Long escenarioId) {
		return reservaRepo.findByEscenarioId(escenarioId);
	}

	/**
	 * Obtiene las reservas realizadas por una persona según su ID.
	 *
	 * @param personaId identificador de la persona
	 * @return lista de reservas de la persona
	 */
	public List<Reserva> findByPersonaId(Long personaId) {
		return reservaRepo.findByPersonaId(personaId);
	}

	/**
	 * Busca reservas dentro de un rango de fecha/hora.
	 *
	 * @param inicioDT fecha y hora de inicio del rango
	 * @param finDT    fecha y hora de fin del rango
	 * @return lista de reservas en el rango especificado
	 */
	public List<Reserva> findByFechaReservaBetween(LocalDateTime inicioDT, LocalDateTime finDT) {
		return reservaRepo.findByFechaReservaBetween(inicioDT, finDT);
	}

	/**
	 * Busca una reserva por su ID y la retorna directamente (sin Optional).
	 *
	 * @param id identificador de la reserva
	 * @return la reserva si existe, o null si no
	 */
	public Reserva findById(Long id) {
		return reservaRepo.findById(id).orElse(null);
	}

}
