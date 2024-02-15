package org.jrotero.proyectoComercio.controller.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private Long id;
	@NotEmpty
	private String name;
	@Min(value = 0)
	private double basePrice;
	@Min(value = 0)
	@Max(value = 1)
	private double discount;
	@NotEmpty
	private String description;
	private Integer stock;

}
