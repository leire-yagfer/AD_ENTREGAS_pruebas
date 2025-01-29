package com.example.ad_entrega9_apihoteljwt;

import com.example.ad_entrega9_apihoteljwt.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class HotelLeireApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelLeireApplication.class, args);
	}
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()

					// PERMITE QUE LA BUSQUEDA DE HOTEL POR LOCALIDAD O CATEGORIA Y LA BUSQUEDA
					// DE UN HOTEL POR TAMAÑO Y PRECIO NO REQUIERAN NINGUN AUTENTIFICACION
					.antMatchers(HttpMethod.GET, "/api/hotelleire/localidad/{localidad}").permitAll()
					.antMatchers(HttpMethod.GET, "/api/hotelleire/categoria/{categoria}").permitAll()
					.antMatchers(HttpMethod.GET, "/api/habitacionleire/filtros/{id_hotel}").permitAll()

					//cualquiera puede entrar a logearse a través de post
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					//.requestMatchers(HttpMethod.POST, "/user").permitAll()
					.anyRequest().authenticated();//cualquier solicitud debe ser autenticada, de lo contrario, mi aplicación Spring devolverá una respuesta 401.
		}
	}
}
