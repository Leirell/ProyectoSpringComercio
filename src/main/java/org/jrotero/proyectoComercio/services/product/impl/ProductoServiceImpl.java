package org.jrotero.proyectoComercio.services.product.impl;

import java.util.List;

import org.jrotero.proyectoComercio.controller.product.IProductMapper;
import org.jrotero.proyectoComercio.controller.product.dto.ProductDto;
import org.jrotero.proyectoComercio.repositories.IProductRepository;
import org.jrotero.proyectoComercio.repositories.IUserRepository;
import org.jrotero.proyectoComercio.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductService {
	@Autowired
	IProductRepository repository;

	@Autowired
	IProductMapper mapper;

	@Autowired
	IUserRepository authenticationRepository;

	@Override
	public ProductDto create(ProductDto dto) throws Exception {
		if (dto.getId() != null) {
			var response = repository.findById(dto.getId()).orElseThrow(() -> new Exception("Error"));
			if (response.getId() == dto.getId()) {
				throw new Exception("Error");
			}

		}
		return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));

	}

	@Override
	public List<ProductDto> getAll() {
		return mapper.entityToDtoList(repository.findAll());
	}

	@Override
	public ProductDto getProduct(Long id) throws Exception {
		var response = repository.findById(id).orElseThrow(() -> new Exception("No existe ese producto"));
		return mapper.entityToDto(response);
	}

	@Override
	public ProductDto buyProduct(Long id, Long idUser) throws Exception {
		var response = repository.findById(id).orElseThrow(() -> new Exception("No existe ese producto"));

		if (response.getStock() <= 0 || response.getStock() == null) {
			throw new Exception("El producto que está comprando está agotado");
		}

		if (authenticationRepository.findById(idUser).get().getAddresses() == null
				|| authenticationRepository.findById(idUser).get().getAddresses().isEmpty()) {
			throw new Exception("No se puede enviar a ninguna dirección");
		}

		response.setStock(response.getStock() - 1);
		return mapper.entityToDto(repository.save(response));

	}

}
