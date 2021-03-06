package org.dam2.appEmt.configuration.security;

import org.dam2.appEmt.configuration.filter.CustomAuthenticationFilter;
import org.dam2.appEmt.configuration.filter.CustomAuthorizationFilter;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuracion de la seguridad de la aplicacion
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * Servicio en el que se validan los usuarios en la base de datos. {@link UsuarioServiceImpl} }
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Herramienta para encriptar la password en el lado servidor.
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //A la hora de autentificar a un usuario, se cifran los datos que han llegado desde el cliente
        //y estos se compraran con una funcion del userDetailsService que esta implementada en la UsuarioServiceImpl,
        //pues lo que hace el sistema de seguridad de Springboot es encriptar lo que llega desde el cliente, dando 
        //igual si se trata de MD5, texto plano o lo que quieras. Lo encripta directamente.
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Este objeto permite personalizar el filtro de autenticacion
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/usuario/login");        

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Permisos de acceso a controladores de usuario
        http.authorizeRequests().antMatchers("/usuario/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuario/insertar").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/usuario/actualizar").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuario/add-rol").hasAuthority(NombreRol.ROLE_ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuario/probar-token").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuario/buscar").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuario/codigo-recuperacion").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/usuario/cambiar-clave").permitAll();
        
        //Permisos de acceso a controladores de favoritos
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/favorito/insertar").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/favorito/actualizar").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/favorito/borrar").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/favorito/obtener-favoritos").authenticated();

        //Permisos de acceso a controladores de MongoDB
        http.authorizeRequests().antMatchers("/mongo/**").hasAuthority(NombreRol.ROLE_ADMIN);

        //Permisos de acceso a controladores de la emt
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/controladores-emt/consultar-parada/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/controladores-emt/listar-paradas").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/controladores-emt/consultar-linea/**").authenticated();

        http.addFilter(customAuthenticationFilter);

        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}