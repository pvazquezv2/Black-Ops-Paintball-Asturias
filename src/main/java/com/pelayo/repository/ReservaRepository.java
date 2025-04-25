package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva, Long> {
	 
	

}
