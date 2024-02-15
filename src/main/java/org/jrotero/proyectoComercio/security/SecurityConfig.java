package org.jrotero.proyectoComercio.security;

import org.jrotero.proyectoComercio.security.filters.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	JwtAuthorizationFilter authorizationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
			throws Exception {
		return httpSecurity.csrf(config -> config.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/v1/user/register", "/v1/user/login", "/v1/product/", "/v1/product/{id}").permitAll();
			auth.anyRequest().authenticated();
		}).sessionManagement(session -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}).addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	/*
	 * @Bean UserDetailsService userDetailsService() { return new
	 * UserDetailsServiceImpl(); }
	 * 
	 * /*
	 * 
	 * @Bean AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
	 * PasswordEncoder encoder) throws Exception { return
	 * httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).
	 * userDetailsService(userDetailsService())
	 * .passwordEncoder(encoder).and().build(); }
	 */

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
