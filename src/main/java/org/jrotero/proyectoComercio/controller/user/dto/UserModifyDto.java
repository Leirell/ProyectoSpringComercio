package org.jrotero.proyectoComercio.controller.user.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyDto {
	@NotNull
	private Long id;

	private String username;

	@Email
	private String email;

	private String password;

	private String telephoneNum;

	private List<AddressDto> addresses;

}
