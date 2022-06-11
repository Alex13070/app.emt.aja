package org.dam2.appEmt.configuration.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de la autorizacion del usuario en nuestro app
 */
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Prefijo del token
     */
    private static final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //En caso de que el usuario se quiera logear, no haremos ninguna validacion, pues no necesita autorizacion
        if (request.getServletPath().equals("/usuario/login")) {
            filterChain.doFilter(request, response);
        } 
        //En otros casos, se tendra que ver si el usuario tiene un token valido.
        else {
            //Buscar el token en el header
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {

                try {
                    //Validacion de token
                    DecodedJWT decodedJWT = decodeToken(authorizationHeader);
                    String username = decodedJWT.getSubject();
                    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

                    //Aqui guardamos los roles del usuario
                    Collection<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    
                    //authorities.forEach(System.out::println);

                    // Aqui creamos la autorizacion que tendra el usuario.
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // System.out.println("\n\n\nEntro al 1 \n\n\n");
                    filterChain.doFilter(request, response);
                } catch (Exception e) {

                    //En caso de error, enviaremos este mensaje
                    log.error("Error, no tienes autorizacion para acceder a este recurso: {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());

                    Map<String, String> error = new HashMap<>();
                    error.put("error", e.getMessage());

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    /**
     * Decodifica el token JWT
     * @param authorizationHeader JWT de autorizacion
     * @return JWT decodificado
     */
    private static DecodedJWT decodeToken(String authorizationHeader) {

        String token = authorizationHeader.substring(PREFIX.length());
        Algorithm algorithm = Algorithm.HMAC256(Constantes.SECRET_KEY.getBytes());

        JWTVerifier verifier = JWT.require(algorithm).build();
        return (DecodedJWT) verifier.verify(token);
    }

    /**
     * Extrae el id del usuario en base a un token
     * @param token JWT token
     * @return User id extraido del token
     */
    public static String getUserIdFromToken (String token) {
        return decodeToken(token).getSubject();
    }
}