package org.jrotero.proyectoComercio.controller.product;

import java.util.List;

import org.jrotero.proyectoComercio.controller.product.dto.ProductDto;
import org.jrotero.proyectoComercio.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	ProductEntity dtoToEntity(ProductDto dto);

	ProductDto entityToDto(ProductEntity entity);

	List<ProductDto> entityToDtoList(List<ProductEntity> entities);

}
