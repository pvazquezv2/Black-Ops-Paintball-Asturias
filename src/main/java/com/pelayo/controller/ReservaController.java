package com.pelayo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pelayo.model.Escenario;
import com.pelayo.model.EstadoReserva;
import com.pelayo.model.Persona;
import com.pelayo.model.Reserva;
import com.pelayo.service.EscenarioService;
import com.pelayo.service.PersonaService;
import com.pelayo.service.ReservaService;

/**
 * Controlador para gestionar las reservas de escenarios, incluyendo creación,
 * visualización y administración.
 */
@Controller
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EscenarioService escenarioService;

	/**
	 * Procesa el envío de una nueva reserva. Valida conflictos de horario, asocia
	 * la reserva a la persona autenticada y guarda la reserva.
	 * 
	 * @param fecha              fecha de la reserva
	 * @param hora               hora de la reserva
	 * @param personas           número de personas
	 * @param nombreEscenario    nombre del escenario
	 * @param modo               modo de juego
	 * @param pack               paquete seleccionado
	 * @param infoAdicional      información extra
	 * @param authentication     info del usuario autenticado
	 * @param redirectAttributes para mensajes flash
	 * @return redirección a página de reservas con mensaje o error
	 */
	@PostMapping("/reservas/enviar")
	public String enviarReserva(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
			@RequestParam("hora") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) java.time.LocalTime hora,
			@RequestParam("personas") int personas, @RequestParam("escenario") String nombreEscenario,
			@RequestParam("modo") String modo, @RequestParam("pack") String pack,
			@RequestParam("infoAdicional") String infoAdicional, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		String nombreUsuario = authentication.getName();
		Optional<Persona> personaOptional = personaService.buscarPorNombreUsuario(nombreUsuario);
		if (personaOptional.isEmpty())
			return "redirect:/error";

		Persona persona = personaOptional.get();
		Escenario escenario = escenarioService.findByNombre(nombreEscenario);
		LocalDateTime fechaHoraReserva = LocalDateTime.of(fecha, hora);

		if (reservaService.existeConflictoReservaConEventoOReserva(fechaHoraReserva, escenario)) {
			redirectAttributes.addFlashAttribute("error",
					"Ya existe una reserva o evento para ese escenario, fecha y hora.");
			return "redirect:/reservar";
		}

		Reserva reserva = new Reserva();
		reserva.setFechaRealizada(LocalDate.now());
		reserva.setFechaReserva(fechaHoraReserva);
		reserva.setNumeroPersonas(personas);
		reserva.setModoJuego(modo);
		reserva.setInfoAdicional(infoAdicional);
		reserva.setEstado(EstadoReserva.PENDIENTE);
		reserva.setPersona(persona);
		reserva.setEscenario(escenario);
		reserva.setPack(pack);

		reservaService.insertar(reserva);
		redirectAttributes.addFlashAttribute("mensaje", "Reserva realizada");
		return "redirect:/reservar";
	}

	/**
	 * Muestra la lista de reservas hechas por el usuario autenticado.
	 * 
	 * @param model modelo para la vista
	 * @param auth  autenticación del usuario
	 * @return vista con las reservas del usuario o redirección a login
	 */
	@GetMapping("/misReservas")
	public String verMisReservas(Model model, Authentication auth) {
		Optional<Persona> personaOpt = personaService.buscarPorNombreUsuario(auth.getName());

		if (personaOpt.isEmpty()) {
			return "redirect:/login";
		}

		List<Reserva> reservas = reservaService.buscarPorPersona(personaOpt.get());

		model.addAttribute("reservas", reservas);
		return "misReservas";
	}

	/**
	 * Página inicial del listado de reservas para administración.
	 * 
	 * @return vista principal del listado reservas admin
	 */
	@GetMapping("/admin/listado_reservas")
	public String menuListadoReservas() {
		return ("admin/listado_reservas");
	}

	/**
	 * Muestra todas las reservas sin filtro para el administrador.
	 * 
	 * @param model modelo para la vista
	 * @param auth  info del usuario autenticado (no usado aquí)
	 * @return vista con todas las reservas
	 */
	@GetMapping("/admin/listado_todas_reservas")
	public String listado_toda_reservas(Model model, Authentication auth) {

		List<Reserva> reservas = reservaService.verTodas();

		model.addAttribute("reservas", reservas);
		return "admin/listado_todas_reservas";
	}

	/**
	 * Lista reservas filtradas por escenario seleccionado.
	 * 
	 * @param escenarioId id opcional del escenario para filtrar
	 * @param model       modelo para la vista
	 * @return vista con reservas del escenario seleccionado
	 */
	@GetMapping("/admin/listado_reservas_escenarios")
	public String listarReservasPorEscenario(@RequestParam(required = false) Long escenarioId, Model model) {
		List<Escenario> escenarios = escenarioService.verTodos();
		List<Reserva> reservas = (escenarioId != null) ? reservaService.findByEscenarioId(escenarioId)
				: new ArrayList<>();

		model.addAttribute("escenarios", escenarios);
		model.addAttribute("reservas", reservas);
		model.addAttribute("escenarioSeleccionadoId", escenarioId);
		return "admin/listado_reservas_escenarios";
	}

	/**
	 * Lista reservas filtradas por persona seleccionada.
	 * 
	 * @param personaId id opcional de la persona para filtrar
	 * @param model     modelo para la vista
	 * @return vista con reservas de la persona seleccionada
	 */
	@GetMapping("/admin/listado_reservas_personas")
	public String listarReservasPorPersona(@RequestParam(required = false) Long personaId, Model model) {
		List<Persona> personas = personaService.verTodas();
		List<Reserva> reservas = (personaId != null) ? reservaService.findByPersonaId(personaId) : new ArrayList<>();

		model.addAttribute("personas", personas);
		model.addAttribute("reservas", reservas);
		model.addAttribute("escenarioSeleccionadoId", personaId);
		return "admin/listado_reservas_personas";
	}

	/**
	 * Lista reservas dentro de un rango de fechas indicado.
	 * 
	 * @param fechaInicio fecha inicio del rango (opcional)
	 * @param fechaFin    fecha fin del rango (opcional)
	 * @param model       modelo para la vista
	 * @return vista con reservas dentro del rango de fechas
	 */
	@GetMapping("/admin/listado_reservas_fechas")
	public String listarPorRangoFechas(
			@RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
			@RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
			Model model) {

		if (fechaInicio != null && fechaFin != null) {

			LocalDateTime inicioDT = fechaInicio.atStartOfDay();
			LocalDateTime finDT = fechaFin.atTime(LocalTime.MAX);

			List<Reserva> reservas = reservaService.findByFechaReservaBetween(inicioDT, finDT);
			model.addAttribute("reservas", reservas);
		}

		return "admin/listado_reservas_fechas";
	}

	/**
	 * Muestra todas las reservas disponibles para eliminar.
	 * 
	 * @param model modelo para la vista
	 * @return vista para eliminar reservas
	 */
	@GetMapping("/admin/eliminar_reserva")
	public String verReservasParaEliminar(Model model) {
		model.addAttribute("reservas", reservaService.verTodas());
		return "admin/eliminar_reserva";
	}

	/**
	 * Elimina la reserva especificada por id.
	 * 
	 * @param id id de la reserva a eliminar
	 * @return redirección a la página de eliminar reservas
	 */
	@PostMapping("/admin/eliminar_reserva/{id}")
	public String eliminarReserva(@PathVariable Long id) {
		reservaService.eliminarPorId(id);
		return "redirect:/admin/eliminar_reserva";
	}

	/**
	 * Muestra todas las reservas con sus estados para modificación.
	 * 
	 * @param model modelo para la vista
	 * @return vista para modificar reservas
	 */
	@GetMapping("/admin/modificar_reserva")
	public String mostrarReservasModificar(Model model) {
		model.addAttribute("reservas", reservaService.verTodas());
		model.addAttribute("estados", EstadoReserva.values());
		return "admin/modificar_reserva";
	}

	/**
	 * Cambia el estado de una reserva según la selección del admin.
	 * 
	 * @param idReserva   id de la reserva a modificar
	 * @param nuevoEstado nuevo estado a asignar
	 * @return redirección a la página de modificar reservas
	 */
	@PostMapping({ "/admin/modificar_reserva", "/admin/modificar_reserva/" })
	public String modificarEstadoReserva(@RequestParam("idReserva") Long idReserva,
			@RequestParam("nuevoEstado") EstadoReserva nuevoEstado) {
		Reserva reserva = reservaService.findById(idReserva);
		if (reserva != null) {
			reserva.setEstado(nuevoEstado);
			reservaService.guardar(reserva);
			System.out.println("Reserva modificada correctamente: " + reserva.getId());
		} else {
			System.out.println("Reserva no encontrada con ID: " + idReserva);
		}
		return "redirect:/admin/modificar_reserva";
	}

}
