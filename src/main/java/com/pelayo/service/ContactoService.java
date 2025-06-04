package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Contacto;
import com.pelayo.repository.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepo;

	public void insertar(Contacto contacto) {
		contactoRepo.saveAndFlush(contacto);
	}

	public List<Contacto> verTodos() {
		return contactoRepo.findAll();
	}

	public void eliminarPorId(Long id) {
		contactoRepo.deleteById(id);
	}

}
