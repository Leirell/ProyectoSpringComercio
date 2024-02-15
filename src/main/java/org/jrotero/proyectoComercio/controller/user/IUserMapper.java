package org.jrotero.proyectoComercio.controller.user;

import org.jrotero.proyectoComercio.controller.user.dto.UserDto;
import org.jrotero.proyectoComercio.controller.user.dto.UserModifyDto;
import org.jrotero.proyectoComercio.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IAddressMapper.class)
public interface IUserMapper {

	UserDto entityToDto(UserEntity entity);

	UserEntity dtoToEntity(UserDto dto);

	UserEntity modifiedDtoToEntity(UserModifyDto dto);

	UserModifyDto entityToModifiedDto(UserEntity entity);

	// List<AddressDto> entityToDtoAddress(List<AddressEntity> entity);

	// List<AddressEntity> dtoToEntityAddress(List<AddressDto> dto);

}
