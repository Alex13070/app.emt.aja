package org.dam2.appEmt;

import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@Import({ SecurityConfig.class })
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	CommandLineRunner run (IUsuarioService usuarioService) {
		return args -> {
			//Usuario administrador y roles.
		};
	}
}
