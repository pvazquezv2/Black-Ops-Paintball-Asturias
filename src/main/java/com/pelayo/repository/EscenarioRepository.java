package com.pelayo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Escenario;

@Repository
public interface EscenarioRepository extends JpaRepository<Escenario, Long> {

	List<Escenario> findByNombreContainingIgnoreCase(String nombre);

	Optional<Escenario> findByNombre(String nombre);

	Escenario findEscenarioById(Long id);

}
