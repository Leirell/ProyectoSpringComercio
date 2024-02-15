package org.jrotero.proyectoComercio.repositories;

import java.util.Optional;

import org.jrotero.proyectoComercio.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);
}
