package org.dam2.appEmt;

import java.time.LocalDate;
import java.util.HashSet;

import org.dam2.appEmt.configuration.cifrado.MD5;
import org.dam2.appEmt.configuration.filter.EmtAuthenticationFilter;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IRolService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Aplicacion a ejecutar
 */
@SpringBootApplication
//@EnableScheduling
public class App {
	
	public static void main(String[] args) {
		//FuncionesEmt.recogerParadas();// --> pruebas
		SpringApplication.run(App.class, args);
	}

	/**
	 * Conficuracion de encriptacion de passwords
	 * @return Algoritmo de encriptacion del app
	 */
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean para incluir el usuario administrador.
	 * @param usuarioService microservicios de usuarios
	 * @param rolService microservicios de rol
	 * @return Codigo a ejecutar.
	 */
	@Bean
	CommandLineRunner run (IUsuarioService usuarioService, IRolService rolService) {
		return args -> {

			usuarioService.insert(
				Usuario.builder()
				.correo(Constantes.CORREO_ADMIN)
				//Encriptado doble para poder acceder al app desde android
				.clave(MD5.encriptar(Constantes.PASSWORD_ADMIN))
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

	/**
	 * Filtro de la EMT
	 * @return Filtro de la EMT
	 */
	@Bean
	FilterRegistrationBean<EmtAuthenticationFilter> filtroEmt () {
		final FilterRegistrationBean<EmtAuthenticationFilter> filtro = new FilterRegistrationBean<>();
		filtro.setFilter(new EmtAuthenticationFilter());
		filtro.addUrlPatterns("/controladores-emt/*");

		return filtro;
	}
}