package org.jrotero.proyectoComercio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double basePrice;
	private double discount;
	private String description;
	private Integer stock;
}
