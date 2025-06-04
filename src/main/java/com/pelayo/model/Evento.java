package com.pelayo.model;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private String categoria;
	private LocalDate fecha;

	private LocalTime horaInicio;

	private LocalTime horaFin;

	private String descripcion;
	private String imagenUrl;

	@ManyToOne
	@JoinColumn(name = "id_escenario")
	private Escenario escenario;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Suscripcion> suscripciones;

	public Evento() {

	}

	public Evento(Long id, String titulo, String categoria, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
			String descripcion, String imagenUrl, Escenario escenario, List<Suscripcion> suscripciones) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
		this.imagenUrl = imagenUrl;
		this.escenario = escenario;
		this.suscripciones = suscripciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}

	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descripcion, escenario, fecha, horaFin, horaInicio, id, imagenUrl, suscripciones,
				titulo);
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", titulo=" + titulo + ", categoria=" + categoria + ", fecha=" + fecha
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", descripcion=" + descripcion
				+ ", imagenUrl=" + imagenUrl + ", escenario=" + escenario + ", suscripciones=" + suscripciones + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(escenario, other.escenario) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(horaFin, other.horaFin) && Objects.equals(horaInicio, other.horaInicio)
				&& Objects.equals(id, other.id) && Objects.equals(imagenUrl, other.imagenUrl)
				&& Objects.equals(suscripciones, other.suscripciones) && Objects.equals(titulo, other.titulo);
	}

	public String getColorClase() {
		switch (categoria) {
		case "Torneo Oficial":
			return "bg-primary";
		case "Evento Temático":
			return "bg-success";
		case "Evento Corporativo":
			return "bg-warning text-dark";
		case "Familiar / Infantil":
			return "bg-info";
		case "Liga":
			return "bg-dark";
		default:
			return "bg-secondary";
		}
	}

}
