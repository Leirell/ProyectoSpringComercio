package org.jrotero.proyectoComercio.services.product;

import java.util.List;

import org.jrotero.proyectoComercio.controller.product.dto.ProductDto;

public interface IProductService {

	ProductDto create(ProductDto dto) throws Exception;

	List<ProductDto> getAll();

	ProductDto getProduct(Long id) throws Exception;

	ProductDto buyProduct(Long id, Long idUser) throws Exception;

}
