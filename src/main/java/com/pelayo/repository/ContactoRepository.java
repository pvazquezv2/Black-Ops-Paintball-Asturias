package com.pelayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayo.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
