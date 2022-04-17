package org.dam2.appEmt.login.controladores;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

import javax.validation.Valid;

// import org.dam2.appEmt.login.modelPeticion.LoginRequest;
// import org.dam2.appEmt.login.modelPeticion.LoginResponse;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @SuppressWarnings("unused")
    private final String secret = "mySecretKey";
	
    @SuppressWarnings("unused")
	private final long tiempo = 600000;
    
    @Autowired
    private IUsuarioService servicioUsuario;

    @PostMapping("/insertar")
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody @Valid Usuario usuario) {

        ResponseEntity<Usuario> respuesta;

        if (servicioUsuario.insert(usuario)) {
            respuesta = new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
        }
        else {
            respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody @Valid Usuario usuario) {

        ResponseEntity<Usuario> respuesta;

        if (servicioUsuario.update(usuario)) {
            respuesta = new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
        }
        else {
            respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;

    }

    /*
    @GetMapping("/obtener-favoritos")
    public ResponseEntity<List<Favorito>> actualizarUsuario(@RequestParam String id) {

        ResponseEntity<List<Favorito>> respuesta;

        List<Favorito> favoritos = servicioUsuario.obtenerFavoritos(id);

        respuesta = new ResponseEntity<>(favoritos, HttpStatus.ACCEPTED);

        return respuesta;

    }

    */

    /*
    @SuppressWarnings("unused")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		//@RequestParam("correo") String correo, @RequestParam("clave") String clave
    	
    	String token = ""; 
        //comprobariamos en la base de datos
        //if(servicioUsuario.findByCorreoAndClave(correo, clave).isPresent())
        //correo.equals("client") && clave.equals("client")
    	if (request.getCorreo().equals("client") && request.getClave().equals("client"))
            token = getJWTToken(request.getCorreo());
			
		return new ResponseEntity<LoginResponse>(new LoginResponse(token), HttpStatus.OK);
		
	}

    */
    
    /*
	private String getJWTToken(String username) {


		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("CLIENT");
		
		String token = Jwts
				.builder()
				.setId("EmtAJA")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tiempo))
				.signWith(SignatureAlgorithm.HS512,
						secret.getBytes()).compact();

		return "Bearer " + token;
	}

    */


    @GetMapping("/funciona")
    public ResponseEntity<String> actualizarUsuario() {

        return new ResponseEntity<>("Funciono", HttpStatus.OK);

    }

}
