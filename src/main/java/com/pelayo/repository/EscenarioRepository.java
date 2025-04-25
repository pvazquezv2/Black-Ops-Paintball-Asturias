package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Escenario;

@Repository
public interface EscenarioRepository extends JpaRepository <Escenario, Long> {

}
