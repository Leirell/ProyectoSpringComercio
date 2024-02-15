package org.jrotero.proyectoComercio.services.user.impl;

import java.util.Arrays;
import java.util.Collection;

import org.jrotero.proyectoComercio.entities.UserEntity;
import org.jrotero.proyectoComercio.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario ".concat(username).concat(" no existe")));

		Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

		return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, authorities);
	}
}
