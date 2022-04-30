package org.dam2.appEmt.login;

import java.time.LocalDate;
import java.util.HashSet;

import org.dam2.appEmt.login.modelo.Sexo;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PruebaLogin {
    
    private final static String INSERTAR = "http://localhost:8080/usuario/insertar";
    private final static String ACTUALIZAR = "http://localhost:8080/usuario/actualizar";

    public static void main(String[] args) {

        /*
        Gson gson = new Gson();


        
        System.out.println(gson.toJson(u));
        */
        Usuario usuario = Usuario.builder()
					.correo(Constantes.CORREO_ADMIN)
					.clave(Constantes.PASSWORD_ADMIN)
					.nombre("Admin")
					.apellidos("Admin")
					.fechaNacimiento(LocalDate.of(2000, 1, 1))
					.sexo(Sexo.NO_ESPECIFICADO)
					.roles(new HashSet<>())
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
