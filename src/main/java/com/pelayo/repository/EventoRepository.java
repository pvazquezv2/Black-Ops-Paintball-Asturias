package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
