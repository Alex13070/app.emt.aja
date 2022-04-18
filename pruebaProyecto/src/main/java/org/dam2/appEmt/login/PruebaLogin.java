package org.dam2.appEmt.login;

import java.time.LocalDate;

import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PruebaLogin {
    
    private static String url = "http://localhost:8080/usuario/{}";

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
            ResponseEntity<Usuario> response = restTemplate.postForEntity(url.replace("{}", "insertar"), usuario, Usuario.class);

            System.out.println(response.getBody()); 

            usuario.setNombre("Pepe");
            
            HttpEntity<Usuario> request = new HttpEntity<>(usuario);
            
            ResponseEntity<Usuario> response2 = restTemplate.exchange(url.replace("{}", "actualizar"), HttpMethod.PUT, request, Usuario.class);

            System.out.println(response2.getBody());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}
