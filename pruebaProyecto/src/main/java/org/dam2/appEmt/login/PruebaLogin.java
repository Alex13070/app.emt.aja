package org.dam2.appEmt.login;

import java.time.LocalDate;

import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PruebaLogin {
    
    private final static String INSERTAR = "http://localhost:8080/usuario/insertar";
    private final static String ACTUALIZAR = "http://localhost:8080/usuario/actualizar";

    public static void main(String[] args) {

        Usuario usuario = Usuario.builder()
            .correo("correo@correo.com")
            .clave("Usisis12!f")
            .nombre("pepe")
            .apellidos("Pepepe")
            .fechaNacimiento(LocalDate.of(2002, 12, 12))
            .sexo(Sexo.HOMBRE)
            .build();


        try {

            RestTemplate restTemplate = new RestTemplate ();
            HttpEntity<Usuario> request = new HttpEntity<>(usuario);
            ResponseEntity<Usuario> response = restTemplate.exchange(INSERTAR, HttpMethod.POST, request, Usuario.class);

            System.out.println(response.getBody()); 

            usuario.setNombre("Pepe");
            
            HttpEntity<Usuario> request2 = new HttpEntity<>(usuario);
            
            ResponseEntity<Usuario> response2 = restTemplate.exchange(ACTUALIZAR, HttpMethod.PUT, request2, Usuario.class);

            System.out.println(response2.getBody());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}
