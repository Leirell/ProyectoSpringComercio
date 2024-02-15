package org.jrotero.proyectoComercio.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jrotero.proyectoComercio.controller.user.dto.UserDto;
import org.jrotero.proyectoComercio.controller.user.dto.UserLoginDto;
import org.jrotero.proyectoComercio.controller.user.dto.UserModifyDto;
import org.jrotero.proyectoComercio.security.jwt.JwtUtils;
import org.jrotero.proyectoComercio.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

	@Autowired
	private IUserService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserDto userDTO, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> error = result.getFieldErrors().stream().map(err -> {
				return "El campo:'" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("errores", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		response.put("response", service.create(userDTO));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLogin, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> error = result.getFieldErrors().stream().map(err -> {
				return "El campo:'" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("errores", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
		User user = (User) authentication.getPrincipal();
		String token = jwtUtils.generateAccessToken(user.getUsername());
		Map<String, Object> httpResponse = new HashMap<>();
		httpResponse.put("token", token);
		response.put("response", httpResponse);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @Valid @RequestBody UserModifyDto userDTO,
			BindingResult result, Authentication authentication) throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> error = result.getFieldErrors().stream().map(err -> {
				return "el campo '" + err.getField() + "' el mensaje " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errores", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (!service.findByUsername(authentication.getName()).getId().equals(id) && userDTO.getId().equals(id)) {
			throw new Exception("This is not your id");
		}

		response.put("User changed to: ", service.update(id, userDTO));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
}
