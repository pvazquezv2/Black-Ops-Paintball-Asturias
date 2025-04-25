package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Suscripcion;

@Repository
public interface SuscripcionRepository extends JpaRepository <Suscripcion, Long> {

}
