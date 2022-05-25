package org.dam2.appEmt.login.controladores;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import javax.validation.Valid;

import org.dam2.appEmt.configuration.filter.CustomAuthorizationFilter;
import org.dam2.appEmt.configuration.mail.EmailService;
import org.dam2.appEmt.login.modelPeticion.ActualizarUsuarioRequest;
import org.dam2.appEmt.login.modelPeticion.AddRolRequest;
import org.dam2.appEmt.login.modelPeticion.CambiarClaveRequest;
import org.dam2.appEmt.login.modelPeticion.UsuarioRequest;
import org.dam2.appEmt.login.modelo.NombreRol;
import org.dam2.appEmt.login.modelo.PasswordResetToken;
import org.dam2.appEmt.login.modelo.Rol;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IPasswordResetTokenService;
import org.dam2.appEmt.login.servicios.IRolService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.dam2.appEmt.utilidades.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;

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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IPasswordResetTokenService passwordResetTokenService;


    /**
     * Controlador para insertar usuarios a la base de datos
     * @param usuario Usuario a insertar
     * @return {@true 202 accepted y usuario insertado}
     *         {@false 400 bad request}
     *         {@exception 500 internal server error}
     */
    @Transactional
    @PostMapping("/insertar")
    public ResponseEntity<UsuarioRequest> insertarUsuario(@Valid @RequestBody UsuarioRequest request) {

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
     *         {@false 40X error}
     *         {@exception 500 internal server error}
     */
    @PutMapping("/actualizar")
    public ResponseEntity<ActualizarUsuarioRequest> actualizarUsuario(@RequestBody ActualizarUsuarioRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<ActualizarUsuarioRequest> respuesta;

        try {

            String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

            Optional<Usuario> find = usuarioService.findById(idUsuario);

            if (find.isPresent()) {
                
                @Valid
                Usuario usuario = find.get();
                logger.info("Token igual {}", passwordEncoder.matches(request.getClave(), usuario.getClave()));                

                if (passwordEncoder.matches(request.getClave(), usuario.getClave())) {

                    usuario.setNombre(request.getNombre());
                    usuario.setApellidos(request.getApellidos());
                    usuario.setClave(request.getClave());
                    usuario.setFechaNacimiento(request.getFechaNacimiento());
                    usuario.setSexo(request.getSexo());

                    if (!request.getNuevaClave().equals("")) {
                        usuario.setClave(request.getNuevaClave());
                    }

                    usuarioService.update(usuario);

                    logger.info("Usuario actualizado");
                    respuesta = new ResponseEntity<>(request, HttpStatus.ACCEPTED);
                }
                else {
                    logger.info("Clave incorrecta");
                    respuesta = new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);
                }
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


    /**
     * Controlador para buscar datos de usuario 
     * @param usuario id del {@link Usuario} y nombre del {@link Rol}
     * @return {@true 202 accepted y usuario}
     *         {@false 400 bad request}
     *         {@exception 500 internal server error}
     */
    @GetMapping("/buscar")
    public ResponseEntity<UsuarioRequest> findById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<UsuarioRequest> respuesta;

        try {

            String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

            Optional<Usuario> optional = usuarioService.findById(idUsuario);
            
            if (optional.isPresent()) {
                Usuario usu = optional.get();
                
                UsuarioRequest usuario = UsuarioRequest.builder()
                    .nombre(usu.getNombre())
                    .apellidos(usu.getApellidos())
                    .fechaNacimiento(usu.getFechaNacimiento())
                    .clave("")
                    .sexo(usu.getSexo())
                    .build();

                respuesta = new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
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


    @PostMapping(value="/codigo-recuperacion")
    public ResponseEntity<Void> codigoRecuperacion(@RequestHeader("correo") String correo) {

        ResponseEntity<Void> response;
        Optional<Usuario> usuario = usuarioService.findById(correo);
        
        try {
            if (usuario.isPresent()) {
                Supplier<Integer> random = () -> new Random().nextInt(10);

                //Crear un codigo de 6 digitos
                String codigo = IntStream.range(0, 6)
                    .mapToObj(i -> random.get().toString())
                    .reduce("", (o1,o2) -> o1+o2);

                Usuario u = usuario.get();

                Optional<PasswordResetToken> token = passwordResetTokenService.findByUsername(correo);
                PasswordResetToken p;

                if (token.isPresent()) {
                    p = token.get();
                    p.setExpiryDate(new Date().getTime() + Constantes.TIEMPO_EXPIRACION_TOKEN_RECUPERACION);
                    p.setToken(codigo);
                }
                else {
                    p = new PasswordResetToken(null, u, codigo, new Date().getTime() + Constantes.TIEMPO_EXPIRACION_TOKEN_RECUPERACION);
                }

                passwordResetTokenService.save(p);
                emailService.sendEmail(correo, codigo);
                response = new ResponseEntity<>(HttpStatus.OK);
                
            }
            else {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (MailException e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }

    @PutMapping(value="/cambiar-clave")
    public ResponseEntity<Void> cambiarClave(@RequestBody CambiarClaveRequest body) {

        ResponseEntity<Void> response;
        Optional<PasswordResetToken> t = passwordResetTokenService.findByUsername(body.getIdUsuario());
        
        try {
            if (t.isPresent()) {
                PasswordResetToken token = t.get();

                if (token.getToken().equals(body.getToken())) {
                    Usuario u = token.getUser();
                    u.setClave(body.getClave());
                    usuarioService.update(u);
                    passwordResetTokenService.deleteByUsername(body.getIdUsuario());
                    response = new ResponseEntity<>(HttpStatus.OK);
                }
                else {
                    response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            else {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (MailException e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
}

