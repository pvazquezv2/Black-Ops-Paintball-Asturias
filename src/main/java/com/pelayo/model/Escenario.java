package com.pelayo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private TipoEscenario tipo;
	
	@Column(nullable = false)
	private String ubicacion;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private String imagen;
	
	@OneToMany(mappedBy = "escenario", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	
	@OneToMany(mappedBy = "escenario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evento> eventos;
	
	public Escenario() {
    }

    public Escenario(String nombre, TipoEscenario tipo, String descripcion, String ubicacion, String imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
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

	public TipoEscenario getTipo() {
		return tipo;
	}

	public void setTipo(TipoEscenario tipo) {
		this.tipo = tipo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
		return Objects.hash(descripcion, id, imagen, nombre, reservas, tipo, ubicacion);
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
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(imagen, other.imagen) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(reservas, other.reservas) && tipo == other.tipo
				&& Objects.equals(ubicacion, other.ubicacion);
	}

	@Override
	public String toString() {
		return "Escenario [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", ubicacion=" + ubicacion
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", reservas=" + reservas + ", eventos="
				+ eventos + "]";
	}
	
	

	
    
    
	
	
	
	

}
