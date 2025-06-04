package com.pelayo.service;

import com.pelayo.model.Persona;
import com.pelayo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class PersonaDetailsService implements UserDetailsService {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaService personaService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Persona persona = personaRepository.findByNombreUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

		return new User(persona.getNombreUsuario(), persona.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority("ROLE_" + persona.getRol().name())));
	}
}
