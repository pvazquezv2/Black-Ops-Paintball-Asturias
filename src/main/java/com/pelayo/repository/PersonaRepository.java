package com.pelayo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	Optional<Persona> findByEmail(String email);

	Optional<Persona> findByNombreUsuario(String nombreUsuario);

	boolean existsByEmail(String email);

	boolean existsByNombreUsuario(String nombreUsuario);

}
