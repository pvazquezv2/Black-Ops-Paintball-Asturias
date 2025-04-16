package com.pelayo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table (name = "contactos")
public class Contacto implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String asunto;
	
	@Column(nullable = false)
	private String mensaje;
	
	@Column(nullable = false)
	private LocalDateTime fechaContacto;
	
	@ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
	
	public Contacto() {}

    public Contacto(String asunto, String mensaje, LocalDateTime fechaContacto, Persona persona) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaContacto = fechaContacto;
        this.persona = persona;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFechaContacto() {
		return fechaContacto;
	}

	public void setFechaContacto(LocalDateTime fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		return Objects.hash(asunto, fechaContacto, id, mensaje, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(asunto, other.asunto) && Objects.equals(fechaContacto, other.fechaContacto)
				&& Objects.equals(id, other.id) && Objects.equals(mensaje, other.mensaje)
				&& Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", asunto=" + asunto + ", mensaje=" + mensaje + ", fechaContacto=" + fechaContacto
				+ ", persona=" + persona + "]";
	}
    
    

}
