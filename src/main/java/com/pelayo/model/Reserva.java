package com.pelayo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva implements Serializable {

	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate fechaRealizada;
	
	@Column(nullable = false)
	private LocalDateTime fechaReserva;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoReserva estado;
	
	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "id_escenario")
	private Escenario escenario;
	
	public Reserva() {
		
	}
	
	public Reserva(LocalDate fechaRealizada, LocalDateTime fechaReserva, Persona persona, Escenario escenario, EstadoReserva estado) {
	    this.fechaRealizada = fechaRealizada;
	    this.fechaReserva = fechaReserva;
	    this.persona = persona;
	    this.escenario = escenario;
	    this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(LocalDate fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, fechaRealizada, fechaReserva, id, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return estado == other.estado && Objects.equals(fechaRealizada, other.fechaRealizada)
				&& Objects.equals(fechaReserva, other.fechaReserva) && Objects.equals(id, other.id)
				&& Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaRealizada=" + fechaRealizada + ", fechaReserva=" + fechaReserva
				+ ", estado=" + estado + ", persona=" + persona + ", escenario=" + escenario + "]";
	}
	
	
	
	
	
	
	
	
	
}
