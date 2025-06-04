package com.pelayo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "escenarios")
public class Escenario implements Serializable {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String ubicacion;

	@OneToMany(mappedBy = "escenario", cascade = CascadeType.ALL)
	private List<Reserva> reservas;

	@OneToMany(mappedBy = "escenario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evento> eventos;

	public Escenario() {
	}

	public Escenario(String nombre, String ubicacion) {
		this.nombre = nombre;

		this.ubicacion = ubicacion;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventos, id, nombre, reservas, ubicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escenario other = (Escenario) obj;
		return Objects.equals(eventos, other.eventos) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(reservas, other.reservas)
				&& Objects.equals(ubicacion, other.ubicacion);
	}

	@Override
	public String toString() {
		return "Escenario [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", reservas=" + reservas
				+ ", eventos=" + eventos + "]";
	}

}
