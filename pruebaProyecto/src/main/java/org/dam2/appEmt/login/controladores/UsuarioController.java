package org.dam2.appEmt.login.controladores;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

import javax.validation.Valid;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.dam2.appEmt.login.modelPeticion.AddRolRequest;
import org.dam2.appEmt.login.modelPeticion.LoginRequest;
import org.dam2.appEmt.login.modelPeticion.LoginResponse;
import org.dam2.appEmt.login.modelPeticion.UsuarioRequest;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.dam2.appEmt.login.modelo.Rol;
// import org.dam2.appEmt.login.modelPeticion.LoginRequest;
// import org.dam2.appEmt.login.modelPeticion.LoginResponse;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.repositorio.UsuarioRepository;
import org.dam2.appEmt.login.servicios.IRolService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.dam2.appEmt.login.servicios.UsuarioServiceImpl;
import org.dam2.appEmt.utilidades.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
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


/**
 * Controladores de usuarios.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    /**
     * Para crear logs.
     */
    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    /**
     * Key de cifrado
     */
    @SuppressWarnings("unused")
    private final String secret = "mySecretKey";
	
    /**
     * Tiempo de expiracion de tokens
     */
    @SuppressWarnings("unused")
	private final long tiempo = 600000;
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    ///Pruebas
    //@Autowired
    //private FavoritoRepository daoFavorito;

    //@Autowired 
    //private UsuarioRepository daoUsuario;
    

    /**
     * Controlador para insertar usuarios a la base de datos
     * @param usuario Usuario a insertar
     * @return {@true 202 accepted y usuario insertado}
     *         {@false 400 bad request}
     */
    @Transactional
    @PostMapping("/insertar")
    public ResponseEntity<UsuarioRequest> insertarUsuario(@RequestBody /*@Valid*/ UsuarioRequest request) {

        ResponseEntity<UsuarioRequest> respuesta;

        try {

            Usuario usuario = Usuario.builder()
                .correo(request.getCorreo())
                .clave(request.getClave())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .sexo(request.getSexo())
                .roles(new HashSet<>())
                .build();

            
            if (usuarioService.insert(usuario)) {
                usuarioService.addRol(usuario.getCorreo(), NombreRol.ROLE_USER);
                respuesta = new ResponseEntity<>(request, HttpStatus.CREATED);
                logger.info("Usuario insertado");
            }
            else {
                logger.info("Error: El usuario ya existe");
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);   
            }

        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error al insertar usuario");
        }

        return respuesta;
    }


    /**
     * Controlador para actualizar usuarios 
     * @param usuario Usuario a actualizar
     * @return {@true 202 accepted y usuario actualizado}
     *         {@false 400 bad request}
     */
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioRequest> actualizarUsuario(@RequestBody @Valid UsuarioRequest request) {

        ResponseEntity<UsuarioRequest> respuesta;

        try {

            Usuario usuario = Usuario.builder()
                .correo(request.getCorreo())
                .clave(request.getClave())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .sexo(request.getSexo())
                .roles(new HashSet<>())
                .build();

            if (usuarioService.update(usuario)) {
                logger.info("Usuario actualizado");
                respuesta = new ResponseEntity<>(request, HttpStatus.ACCEPTED);
            }
            else {
                logger.info("El usuario no existe");
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error al actualizar usuario");
        }

        return respuesta;

    }
    /*
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUsuario(@RequestBody LoginRequest entity) {

        ResponseEntity<LoginResponse> respuesta;

        try {

            Optional<Usuario> usuario = usuarioService.findByCorreoAndClave(entity.getCorreo(), entity.getClave());

            if (usuario.isPresent()) {
                
                //Crear token
                String token = "";
                
                LoginResponse responseEntity = new LoginResponse(token);

                //Guardar en la base de datos, en la tabla de Keys
                logger.info("Se ha hecho login");
                respuesta = new ResponseEntity<>(responseEntity, HttpStatus.ACCEPTED);
            }
            else {
                logger.info("El usuario no existe");
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error al actualizar usuario");
        }

        return respuesta;

    }
    */
    @PostMapping("/add-rol")
    public ResponseEntity<Void> addRolUsuario(@RequestBody AddRolRequest entity) {

        ResponseEntity<Void> respuesta;

        try {

            Optional<Usuario> usuario = usuarioService.findById(entity.getCorreo());
            Optional<Rol> rol = rolService.findByNombre(entity.getRol());
            if (usuario.isPresent() && rol.isPresent()) {
                
                if (usuarioService.addRol(entity.getCorreo(), entity.getRol())) {
                    logger.info("Rol added");
                    respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
                }
                else { 
                    logger.info("No se puede add el rol");
                    respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            else {
                logger.info("El usuario no existe");
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error al add rol al usuario");
        }

        return respuesta;

    }

    
    @SuppressWarnings("unused")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(/*@RequestBody*/ LoginRequest request) {
		//@RequestParam("correo") String correo, @RequestParam("clave") String clave
        
        Algorithm algorithm = Algorithm.HMAC256(Constantes.SECRET_KEY.getBytes());
    	
        String token = ""; 
        //comprobariamos en la base de datos
        String correo = request.getCorreo();
        
        //buscar en base de datos
        Optional<Usuario> usuario = usuarioService.findById(correo);

        ResponseEntity<LoginResponse> resp = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
        
        if(usuario.isPresent()){ //mira que haya encontrado alguno

            String contrasenia = usuario.get().getClave(); //si lo tiene busca su clave
            System.err.println("--------------"+contrasenia+"------------------");
            System.err.println("--------------"+request.getClave()+"------------------");

            if (request.getClave().equals(contrasenia)){ //compara con la que se le ha pasado
                //generar token
                token = JWT.create()
                .withSubject(correo)
                .withExpiresAt(new Date(System.currentTimeMillis() + Constantes.TIEMPO_EXPIRACION))
                //.withIssuer(request.getRequestURL().toString())
                //.withClaim("roles",
                //        usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                //                .collect(Collectors.toList()))
                .sign(algorithm);
            }
        
			resp = new ResponseEntity<LoginResponse>(new LoginResponse(token), HttpStatus.OK);
        }else{
            resp = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
        }
        
        return resp;
	}
    
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

    /*
    @GetMapping("/funciona")
    public ResponseEntity<String> actualizarUsuario() {

        Usuario usuario = Usuario.builder()
            .correo("correo@correo.com")
            .clave("Usisis12!f")
            .nombre("pepe")
            .apellidos("Pepepe")
            .fechaNacimiento(LocalDate.of(2002, 12, 12))
            .sexo(Sexo.HOMBRE)
            .build();


        daoUsuario.save(usuario);


        logger.info("Insertando usuaurio");

        Favorito fav = Favorito.builder()
            .id(
                FavoritoPK.builder()
                    .idFavorito("5111")
                    .usuario(usuario).build()
                ).
            nombreParada("Parada")
            .build();

            Favorito fav2 = Favorito.builder()
            .id(
                FavoritoPK.builder()
                    .idFavorito("5111")
                    .usuario(usuario).build()
                ).
            nombreParada("Parada")
            .build();

        //Usuario.builder().id(2345L).build();

        daoFavorito.save(fav);
        daoFavorito.save(fav2);

        daoFavorito.findAll().forEach(System.out::println);

        return new ResponseEntity<>("Ayuda porfavor", HttpStatus.OK);

    }
    */
}

