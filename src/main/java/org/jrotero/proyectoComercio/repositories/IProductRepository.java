package org.jrotero.proyectoComercio.repositories;

import java.util.List;

import org.jrotero.proyectoComercio.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
	List<ProductEntity> findAll();

}
