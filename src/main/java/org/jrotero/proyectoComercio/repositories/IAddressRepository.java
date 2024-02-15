package org.jrotero.proyectoComercio.repositories;

import org.jrotero.proyectoComercio.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<AddressEntity, Long> {

}
