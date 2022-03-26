package org.dam2.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class PruebaProyectoApplication {
//main
//me ves?
	public static void main(String[] args) {
		SpringApplication.run(PruebaProyectoApplication.class, args);
	}

}
