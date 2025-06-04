package com.pelayo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Persona;
import com.pelayo.model.Suscripcion;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

	List<Suscripcion> findByPersonaId(Long personaId);

	Optional<Suscripcion> findByPersonaIdAndEventoId(Long personaId, Long eventoId);

	List<Suscripcion> findByPersona(Persona persona);
}
