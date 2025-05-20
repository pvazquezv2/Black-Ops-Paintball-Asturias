package com.pelayo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.pelayo.model.Persona;
import com.pelayo.repository.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepo;
	
	public void insertar(Persona p) {
		personaRepo.saveAndFlush(p);
	}
	
	public Collection<Persona> verTodas(){
		return personaRepo.findAll();
	}
	
	public Optional<Persona> buscarPorId(Long id){
		return personaRepo.findById(id);
	}
	
	public Persona buscarPorEmail(String email) {
		return personaRepo.findByEmail(email);
	}
	
	public Persona buscarPorNombreUsuario(String nombreUsuario) {
		return personaRepo.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean emailExiste(String email) {
        return personaRepo.existsByEmail(email);
    }

    public boolean nombreUsuarioExiste(String nombreUsuario) {
        return personaRepo.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean validarPersona(Persona pers) {
        if (pers == null) {
            return false;
        }
        if (pers.getNombre() == null || pers.getNombre().isEmpty()) { 
            return false;
        }
        if (pers.getNombre().length() < 3 || pers.getNombre().length() > 20) {
            return false;
        }
        if (pers.getEmail() == null || pers.getEmail().isEmpty()) {
            return false;
        }
        if (pers.getEmail().length() < 5 || !pers.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") || pers.getEmail().length() > 40) {
            return false;
        }
		return true;
    }
    
    @Transactional
    @Modifying
    public boolean borrarPersona(Long id) {
        try {
            if (personaRepo.existsById(id)) {
                personaRepo.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la persona: " + e.getMessage());
            return false;
        }
    }
	public Optional<Persona> buscarPorId(long idUsuario) {
		return personaRepo.findById(idUsuario);
	}
	
	public Persona buscarPersonaPorUsuario(String usuario) {
	    Persona persona = personaRepo.findByNombreUsuario(usuario);
	    return persona;
	}
    
   
}
