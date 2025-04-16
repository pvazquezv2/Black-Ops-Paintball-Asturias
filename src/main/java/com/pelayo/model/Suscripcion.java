package com.pelayo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "suscripciones")
public class Suscripcion implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate fechaRealizada;
	
	@ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    
    public Suscripcion() {
    	
    }

	public Suscripcion(LocalDate fechaRealizada) {
		super();
		this.fechaRealizada = fechaRealizada;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(evento, fechaRealizada, id, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suscripcion other = (Suscripcion) obj;
		return Objects.equals(evento, other.evento) && Objects.equals(fechaRealizada, other.fechaRealizada)
				&& Objects.equals(id, other.id) && Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Suscripcion [id=" + id + ", fechaRealizada=" + fechaRealizada + ", persona=" + persona + ", evento="
				+ evento + "]";
	}
    
    
	
	

}
