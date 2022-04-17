package org.dam2.appEmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// import org.dam2.appEmt.configuration.JWTAuthorizationFilter;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class PruebaProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaProyectoApplication.class, args);
	}

	/*

	Aqui habria que crear una funcion onStart en la que hagamos de alguna manera lo del 
	token de inicio de sesion automaticamente. Yo propongo crear un hilo que cada 86000
	o buscar alguna manera de que algo se ejecute cada x tiempo. 

	Yo opino que lo mas facil de hacer es lo del hilo bastante simple que sea estilo:

	while true
		sleep 86000000 --> porque va en milis
		try 
			loginController
		catch
			bum?


	
	Si veis alguna forma mas bonita de hacer esto, comentadlo por whatsapp o por discord y lo
	vemos a ver que tal.

	*/
	/*
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated();
		}
	}
	*/

}
