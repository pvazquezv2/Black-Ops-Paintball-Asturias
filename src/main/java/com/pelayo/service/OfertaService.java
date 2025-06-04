package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Oferta;
import com.pelayo.repository.OfertaRepository;

@Service
public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepo;

	public List<Oferta> verTodas() {
		return ofertaRepo.findAll();
	}

	public void eliminarPorId(Long id) {
		ofertaRepo.deleteById(id);
	}

	public void guardar(Oferta oferta) {
		ofertaRepo.save(oferta);
	}

}
