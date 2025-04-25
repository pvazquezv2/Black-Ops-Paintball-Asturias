package com.pelayo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Escenario;

@Repository
public interface EscenarioRepository extends JpaRepository <Escenario, Long> {

	List<Escenario> findByNombreContainingIgnoreCase(String nombre);
}
