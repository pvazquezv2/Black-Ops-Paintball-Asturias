package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

}
