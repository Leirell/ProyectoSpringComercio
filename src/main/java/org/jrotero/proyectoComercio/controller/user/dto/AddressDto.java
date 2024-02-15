package org.jrotero.proyectoComercio.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private String name;
	private String street;
	private String city;
	private String province;
	private String zipCode;

}
