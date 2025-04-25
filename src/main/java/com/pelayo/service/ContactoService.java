package com.pelayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Contacto;
import com.pelayo.repository.ContactoRepository;

import jakarta.transaction.Transactional;

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

    public Optional<Contacto> buscarPorId(Long id) {
        return contactoRepo.findById(id);
    }

    @Transactional
    @Modifying
    public boolean borrarContacto(Long id) {
        if (contactoRepo.existsById(id)) {
            contactoRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
