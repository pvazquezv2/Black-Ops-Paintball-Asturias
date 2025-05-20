package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	Persona findByEmail (String email);
	Persona findByNombreUsuario (String nombreUsuario);
	boolean existsByEmail(String email);
	boolean existsByNombreUsuario(String nombreUsuario);
	


}
