package com.pelayo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contactos")
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

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido1;

	@Column(nullable = false)
	private String apellido2;

	@Column(nullable = false)
	private String email;

	public Contacto() {
	}

	public Contacto(String nombre, String apellido1, String apellido2, String email, String asunto, String mensaje,
			LocalDateTime fechaContacto) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fechaContacto = fechaContacto;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido1, apellido2, asunto, email, fechaContacto, id, mensaje, nombre);
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
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& Objects.equals(asunto, other.asunto) && Objects.equals(email, other.email)
				&& Objects.equals(fechaContacto, other.fechaContacto) && Objects.equals(id, other.id)
				&& Objects.equals(mensaje, other.mensaje) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", asunto=" + asunto + ", mensaje=" + mensaje + ", fechaContacto=" + fechaContacto
				+ ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + email
				+ "]";
	}

}
