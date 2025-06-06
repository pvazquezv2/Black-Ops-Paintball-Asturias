package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Contacto;
import com.pelayo.repository.ContactoRepository;

/**
 * Servicio encargado de gestionar los mensajes de contacto que envían los
 * usuarios
 */
@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepo;

	/**
	 * Guarda un nuevo mensaje de contacto en la base de datos (flush para asegurar
	 * que se guarda inmediatamente)
	 */
	public void insertar(Contacto contacto) {
		contactoRepo.saveAndFlush(contacto);
	}

	/**
	 * Devuelve la lista de todos los mensajes enviados desde el formulario de
	 * contacto
	 */
	public List<Contacto> verTodos() {
		return contactoRepo.findAll();
	}

	/**
	 * Elimina un mensaje de contacto por su ID (borrado simple)
	 */
	public void eliminarPorId(Long id) {
		contactoRepo.deleteById(id);
	}

}
