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
@Table(name = "reservas")
public class Reserva implements Serializable {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fechaRealizada;

	@Column(nullable = false)
	private LocalDateTime fechaReserva;

	@Column(nullable = false)
	private int numeroPersonas;

	@Column(nullable = false)
	private String pack;

	@Column(length = 500)
	private String infoAdicional;

	@Column(nullable = false)
	private String modoJuego;

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

	public Reserva(Long id, LocalDate fechaRealizada, LocalDateTime fechaReserva, int numeroPersonas, String pack,
			String infoAdicional, String modoJuego, EstadoReserva estado, Persona persona, Escenario escenario) {
		super();
		this.id = id;
		this.fechaRealizada = fechaRealizada;
		this.fechaReserva = fechaReserva;
		this.numeroPersonas = numeroPersonas;
		this.pack = pack;
		this.infoAdicional = infoAdicional;
		this.modoJuego = modoJuego;
		this.estado = estado;
		this.persona = persona;
		this.escenario = escenario;
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

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public String getModoJuego() {
		return modoJuego;
	}

	public void setModoJuego(String modoJuego) {
		this.modoJuego = modoJuego;
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
		return Objects.hash(escenario, estado, fechaRealizada, fechaReserva, id, infoAdicional, modoJuego,
				numeroPersonas, pack, persona);
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
		return Objects.equals(escenario, other.escenario) && estado == other.estado
				&& Objects.equals(fechaRealizada, other.fechaRealizada)
				&& Objects.equals(fechaReserva, other.fechaReserva) && Objects.equals(id, other.id)
				&& Objects.equals(infoAdicional, other.infoAdicional) && Objects.equals(modoJuego, other.modoJuego)
				&& numeroPersonas == other.numeroPersonas && Objects.equals(pack, other.pack)
				&& Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaRealizada=" + fechaRealizada + ", fechaReserva=" + fechaReserva
				+ ", numeroPersonas=" + numeroPersonas + ", pack=" + pack + ", infoAdicional=" + infoAdicional
				+ ", modoJuego=" + modoJuego + ", estado=" + estado + ", persona=" + persona + ", escenario="
				+ escenario + "]";
	}

}
