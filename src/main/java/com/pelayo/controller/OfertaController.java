package com.pelayo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pelayo.model.Oferta;
import com.pelayo.service.OfertaService;

/**
 * Controlador que se encarga de mostrar, añadir y eliminar ofertas.
 * Tanto en la parte pública como desde el panel de administración.
 */
@Controller
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;

	/**
	 * Muestra la página pública de precios/ofertas para cualquier visitante.
	 */
	@GetMapping("/precios")
	public String mostrarOfertas(Model model) {
		List<Oferta> ofertas = ofertaService.verTodas();
		model.addAttribute("ofertas", ofertas);
		return "precios";
	}

	/**
	 * Página para el admin donde se ve un listado de todas las ofertas disponibles.
	 */
	@GetMapping("/admin/listado_ofertas")
	public String listado_ofertas(Model model, Authentication auth) {

		List<Oferta> ofertas = ofertaService.verTodas();

		model.addAttribute("ofertas", ofertas);
		return "admin/listado_ofertas";
		
	}

	/**
	 * Página para elegir qué oferta se quiere eliminar.
	 */
	@GetMapping("/admin/eliminar_oferta")
	public String verOfertasParaEliminar(Model model) {
		model.addAttribute("ofertas", ofertaService.verTodas());
		return "admin/eliminar_oferta";
	}

	/**
	 * Acción que elimina una oferta concreta según su ID.
	 */
	@PostMapping("/admin/eliminar_oferta/{id}")
	public String eliminarOferta(@PathVariable Long id) {
		ofertaService.eliminarPorId(id);
		return "redirect:/admin/eliminar_oferta";
	}

	/**
	 * Muestra el formulario para que el admin cree una nueva oferta.
	 */
	@GetMapping("/admin/nueva_oferta")
	public String verNuevasOfertas(Model model) {
		model.addAttribute("oferta", new Oferta());

		return "admin/nueva_oferta";
	}

	/**
	 * Guarda la nueva oferta creada desde el formulario.
	 */
	@PostMapping("/admin/nueva_oferta")
	public String guardarNuevaOferta(@ModelAttribute Oferta oferta) {
		ofertaService.guardar(oferta);
		return "redirect:/admin/nueva_oferta";
	}

}
