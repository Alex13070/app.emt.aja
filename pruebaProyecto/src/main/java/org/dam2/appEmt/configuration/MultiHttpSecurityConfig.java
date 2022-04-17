package org.dam2.appEmt.configuration;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
public class MultiHttpSecurityConfig {

	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //No password encrypt for demo proposes only (do NOT use in Prod environments)!!
		System.out.println("estoy cargando los usuarios y contraseñas para la autentificación básica");
        auth.inMemoryAuthentication()
            .withUser("client")
            .password("{noop}client")
            .roles("CLIENT");
        
        auth.inMemoryAuthentication()
        .withUser("client2")
        .password("{noop}client2")
        .roles("CLIENT");
    }
	
	 @Configuration
     //@Order(1)  
	 public static class SecurityConfigToken extends WebSecurityConfigurerAdapter {
	
		 @Override
		    protected void configure(HttpSecurity http) throws Exception {
			 System.out.println("estoy cargando autorización con token para controlador /token");

		    	http.csrf().disable().
		    		addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class).
		    		antMatcher("/login/**").authorizeRequests().
		    		antMatchers(HttpMethod.POST, "/login").permitAll().
		    		antMatchers("/login/listar-parada").authenticated(); 
		    		
		    }
		 
	 }
	 
	 @Configuration
     //@Order(2)  
	 public static class SecurityConfigUser extends WebSecurityConfigurerAdapter {
	
		 @Override
		    protected void configure(HttpSecurity http) throws Exception {
			 System.out.println("estoy cargando autorización básica para controlador /istar parada");
		        http.csrf().disable()
		        	.antMatcher("/listar-parada/**")
		            .authorizeRequests()
		            .anyRequest()
		            .hasRole("CLIENT")    
		            .and().httpBasic();
		        
		    }
		 
	 }
	 
	 
	
}*/