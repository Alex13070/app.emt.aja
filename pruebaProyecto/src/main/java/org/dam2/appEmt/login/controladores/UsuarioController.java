package org.dam2.appEmt.login.controladores;

import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dam2.appEmt.login.modelPeticion.AddRolRequest;
import org.dam2.appEmt.login.modelPeticion.UsuarioRequest;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.dam2.appEmt.login.modelo.Rol;
// import org.dam2.appEmt.login.modelPeticion.LoginRequest;
// import org.dam2.appEmt.login.modelPeticion.LoginResponse;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IRolService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    /**
     * Inyeccion de dependencias de los microservicios de {@link Usuario}
     */
    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Inyeccion de dependencias de los microservicios de {@link Rol}
     */
    @Autowired
    private IRolService rolService;


    /**
     * Controlador para insertar usuarios a la base de datos
     * @param usuario Usuario a insertar
     * @return {@true 202 accepted y usuario insertado}
     *         {@false 400 bad request}
     *         {@exception 500 internal server error}
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
     *         {@exception 500 internal server error}
     */
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioRequest> actualizarUsuario(@RequestBody @Valid UsuarioRequest request) {

        ResponseEntity<UsuarioRequest> respuesta;

        try {

            Optional<Usuario> find = usuarioService.findById(request.getCorreo());

            if (find.isPresent()) {
                
                Usuario usuario = find.get();

                usuario.setNombre(request.getNombre());
                usuario.setApellidos(request.getApellidos());
                usuario.setClave(request.getClave());
                usuario.setFechaNacimiento(request.getFechaNacimiento());
                usuario.setSexo(request.getSexo());

                usuarioService.update(usuario);

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
    
    /**
     * Controlador para actualizar roles de usuarios 
     * @param usuario id del {@link Usuario} y nombre del {@link Rol}
     * @return {@true 202 accepted y usuario actualizado}
     *         {@false 400 bad request}
     *         {@exception 500 internal server error}
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

    /**
     * Controlador para probar si el token es valido
     * @return 200 ok
     */
    @GetMapping("/probar-token")
    public ResponseEntity<Void> tokenOperativo(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

