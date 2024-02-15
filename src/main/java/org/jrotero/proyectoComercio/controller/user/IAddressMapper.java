package org.jrotero.proyectoComercio.controller.user;

import org.jrotero.proyectoComercio.controller.user.dto.AddressDto;
import org.jrotero.proyectoComercio.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {
	AddressEntity dtoToEntity(AddressDto dto);

	AddressDto entityToDto(AddressEntity entity);

}
