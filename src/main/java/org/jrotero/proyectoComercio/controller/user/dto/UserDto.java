package org.jrotero.proyectoComercio.controller.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;

	@NotEmpty
	private String username;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

}
