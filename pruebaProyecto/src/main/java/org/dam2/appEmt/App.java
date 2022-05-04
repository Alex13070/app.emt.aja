package org.dam2.appEmt;

import java.time.LocalDate;
import java.util.HashSet;

import org.dam2.appEmt.configuration.MD5;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IRolService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	//Posible bum en CustomAuthenticationFilter y CustomAuthorizationFilter
	@Bean
	Algorithm algorithm() {
		return Algorithm.HMAC256(Constantes.SECRET_KEY.getBytes());
	}
	*/
	@Bean
	CommandLineRunner run (IUsuarioService usuarioService, IRolService rolService) {
		return args -> {

			usuarioService.insert(
				Usuario.builder()
				.correo(Constantes.CORREO_ADMIN)
				.clave(MD5.encriptar( Constantes.PASSWORD_ADMIN))
				.nombre("Admin")
					.apellidos("Admin")
					.fechaNacimiento(LocalDate.of(2000, 1, 1))
					.sexo(Sexo.NO_ESPECIFICADO)
					.roles(new HashSet<>())
					.build()
			);

			rolService.saveRol(NombreRol.ROLE_USER); 
			rolService.saveRol(NombreRol.ROLE_ADMIN);

			usuarioService.addRol(Constantes.CORREO_ADMIN, NombreRol.ROLE_ADMIN);
			usuarioService.addRol(Constantes.CORREO_ADMIN, NombreRol.ROLE_USER);

		};
	}
}