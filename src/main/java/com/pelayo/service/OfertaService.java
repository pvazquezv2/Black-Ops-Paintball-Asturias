package com.pelayo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayo.model.Oferta;
import com.pelayo.repository.OfertaRepository;

/**
 * Servicio encargado de la gestión de las ofertas. Permite ver todas las
 * ofertas, guardar nuevas o modificar existentes, y eliminar por ID.
 */
@Service
public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepo;

	/**
	 * Recupera la lista completa de ofertas registradas en el sistema.
	 * 
	 * @return lista de todas las ofertas
	 */
	public List<Oferta> verTodas() {
		return ofertaRepo.findAll();
	}

	/**
	 * Elimina una oferta específica según su identificador.
	 * 
	 * @param id identificador de la oferta a eliminar
	 */
	public void eliminarPorId(Long id) {
		ofertaRepo.deleteById(id);
	}

	/**
	 * Guarda una nueva oferta o actualiza una existente.
	 * 
	 * @param oferta objeto Oferta a persistir
	 */
	public void guardar(Oferta oferta) {
		ofertaRepo.save(oferta);
	}

}
