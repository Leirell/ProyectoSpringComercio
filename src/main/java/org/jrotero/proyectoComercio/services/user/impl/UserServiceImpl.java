package org.jrotero.proyectoComercio.services.user.impl;

import org.jrotero.proyectoComercio.controller.user.IUserMapper;
import org.jrotero.proyectoComercio.controller.user.dto.UserDto;
import org.jrotero.proyectoComercio.controller.user.dto.UserModifyDto;
import org.jrotero.proyectoComercio.repositories.IUserRepository;
import org.jrotero.proyectoComercio.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;

	@Autowired
	IUserMapper mapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	// Crea un usuario con los datos indispensables (Nombre, Contraseña y correo),
	// además, encodea la contraseña para que esta sea segura
	@Override
	public UserDto create(UserDto dto) throws Exception {
		if (dto.getId() != null) {
			var response = repository.findById(dto.getId()).orElseThrow(() -> new Exception("Error"));
			if (response.getId() == dto.getId()) {
				throw new Exception("Error");
			}
		}
		var username = repository.findByUsername(dto.getUsername());
		if (!username.isEmpty()) {
			throw new Exception("Error Username duplicated");
		}
		var entity = mapper.dtoToEntity(dto);
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return mapper.entityToDto(repository.save(entity));
	}

	// Permite modificar un usario, dándole datos adicionales requeridos para poder
	// comprar(dirección numero de teléfono)
	@Override
	public UserModifyDto update(Long id, UserModifyDto dto) throws Exception {

		var entity = mapper.modifiedDtoToEntity(dto);
		if (entity.getPassword() != null) {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		}

		return mapper.entityToModifiedDto(repository.save(entity));
	}

	@Override
	public UserDto findByUsername(String username) throws Exception {
		var response = repository.findByUsername(username).orElseThrow(() -> new Exception("Error"));
		return mapper.entityToDto(response);
	}

}
