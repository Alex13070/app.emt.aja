package org.dam2.appEmt.configuration.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import lombok.extern.slf4j.Slf4j;

/**
 * Filtro de autentificacion del app
 */
//@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	/**
	 * Constructor de la clase. Generado es generado internanmente por Springboot.
	 * @param auth Encargado de procesar la autentificacion
	 */
	public CustomAuthenticationFilter(AuthenticationManager auth) {
		this.authenticationManager = auth;
	}

	/**
	 *	Encargada de hacer la autentificacion mediante los parametros pasados como headers. Se encarga de buscar en la base de 
	 *  datos para hacer la autentificacion mediante un servicio implementado.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		//Parametros de headers
		String username = request.getParameter("correo");
		String password = request.getParameter("password");

		// log.info("Correo: " + (username == null ? "no va" : username));
		// log.info("Clave: " + (password == null ? "no va" : password));

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authenticationToken);
	}

	/**
	 * Se ejecuta en caso de que la autentificacion sea correcta. Se encarga de generar el objeto que se devuelve en la peticion
	 * con el token JWT metido en su interior.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) 
		throws IOException, ServletException {
			
		User usuario = (User) authentication.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(Constantes.SECRET_KEY.getBytes());

		String accessToken = JWT.create()
				.withSubject(usuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + Constantes.TIEMPO_EXPIRACION))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
					usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);


		// En este map se introducen los valores para crear el JSON
		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", accessToken);
		// tokens.put("refreshToken", refreshToken);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		// Esto se encarga de crear un objeto JSON en base al map creado para devolverlo como respuesta.
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);

	}
}
