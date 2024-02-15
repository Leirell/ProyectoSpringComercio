package org.jrotero.proyectoComercio.services.user;

import org.jrotero.proyectoComercio.controller.user.dto.UserDto;
import org.jrotero.proyectoComercio.controller.user.dto.UserModifyDto;

public interface IUserService {

	UserDto create(UserDto dto) throws Exception;

	UserModifyDto update(Long id, UserModifyDto dto) throws Exception;

	UserDto findByUsername(String username) throws Exception;

}
