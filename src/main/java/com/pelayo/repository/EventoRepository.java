package com.pelayo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Escenario;
import com.pelayo.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	List<Evento> findByEscenarioAndFecha(Escenario escenario, LocalDate fecha);

}
