package org.jrotero.proyectoComercio.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jrotero.proyectoComercio.controller.product.dto.ProductDto;
import org.jrotero.proyectoComercio.services.product.IProductService;
import org.jrotero.proyectoComercio.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/product/")
public class ProductController {

	@Autowired
	private IProductService service;
	@Autowired
	private IUserService serviceAuthentication;

	@PostMapping("/sell")
	public ResponseEntity<?> register(@Valid @RequestBody ProductDto dto, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> error = result.getFieldErrors().stream().map(err -> {
				return "El campo: '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("errores", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("response", service.create(dto));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		Map<String, Object> response = new HashMap<>();

		response.put("response", service.getAll());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) throws Exception {
		Map<String, Object> response = new HashMap<>();

		response.put("response", service.getProduct(id));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);

	}

	@PutMapping("/buy/{id}")
	public ResponseEntity<?> buy(@PathVariable Long id, Authentication authentication) throws Exception {
		Map<String, Object> response = new HashMap<>();

		var idUser = serviceAuthentication.findByUsername(authentication.getName()).getId();
		response.put("response", service.buyProduct(id, idUser));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);

	}

}
