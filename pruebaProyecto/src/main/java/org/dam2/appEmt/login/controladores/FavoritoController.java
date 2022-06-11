package org.dam2.appEmt.login.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dam2.appEmt.login.modelPeticion.BorrarFavoritoRequest;
import org.dam2.appEmt.login.modelPeticion.FavoritoResponse;
import org.dam2.appEmt.configuration.filter.CustomAuthorizationFilter;
import org.dam2.appEmt.configuration.logs.Logs;
import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IFavoritoService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador de favoritos.
 */
@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    
    /**
     * Creador de logs
     */
    private Logger logger = LoggerFactory.getLogger(FavoritoController.class);


    /**
	 * Microservicio de favorito
	 */
    @Autowired
    private IFavoritoService favoritoService;

    /**
	 * Microservicio de usuario
	 */
    @Autowired
    private IUsuarioService usuarioService;


    /**
     * Controlador encargado de guardar favoritos en la base de datos
     * @param favorito Favorito a introducir en la base de datos
     * @return {@true 201 created y favorito introducido}
     *         {@false 400 bad request}
     *         {@exception 500 internalServer error}
     */
    @PostMapping("/insertar")
    public ResponseEntity<FavoritoResponse> guardarFavorito(@Valid @RequestBody FavoritoResponse entity, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<FavoritoResponse> respuesta;
        String msg;
        String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

        try {

            Optional<Usuario> usuario = usuarioService.findById(idUsuario);

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            Favorito favorito = Favorito.builder()
                .id(
                    FavoritoPK.builder()
                        .usuario(usuario.get())
                        .idFavorito(entity.getIdParada())
                        .build()
                )
                .nombreParada(entity.getNombreParada())
                .build();


            if (favoritoService.save(favorito)){
                respuesta = new ResponseEntity<>(entity, HttpStatus.CREATED);
                logger.info("Favorito insertado");
                msg = "Favorito insertado";
            }
            else {
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                logger.info("Error: El favorito ya existe");
                msg = "El favorito ya existe";
            }
        } catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al insertar favorito");
            msg = "Error al insertar favorito " + ((e.getMessage() != null)?e.getMessage():"");
            
        }
        
        new Thread(new Logs(LocalDateTime.now(), "FavoritoController(guardarFavorito)", msg)).start();

        return respuesta;
    }

    /**
     * Controlador encargado de actualizar favoritos en la base de datos
     * @param favorito Favorito a introducir en la base de datos
     * @return {@true 202 accepted y favorito actualizado}
     *         {@false 400 bad request}
     *         {@exception 500 internalServer error}
     */
    @PutMapping("/actualizar")
    public ResponseEntity<FavoritoResponse> actualizarFavorito (@Valid @RequestBody FavoritoResponse entity, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<FavoritoResponse> respuesta;
        String msg;
        String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

        try {

            Optional<Usuario> usuario = usuarioService.findById(idUsuario);

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            @Valid
            Favorito favorito = Favorito.builder()
                .id(
                    FavoritoPK.builder()
                        .usuario(usuario.get())
                        .idFavorito(entity.getIdParada())
                        .build()
                )
                .nombreParada(entity.getNombreParada())
                .build();

            if (favoritoService.update(favorito)){
                respuesta = new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
                logger.info("Favorito actualizado");
                msg = "Favorito actualizado";
            }
            else {
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                logger.info("Error: El favorito a actualizar no existe");
                msg = "El favorito a actualizar no existe";
            }

        } catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al insertar favorito");
            msg = "Error al insertar favorito " + ((e.getMessage() != null)?e.getMessage():"");
        }

        
        new Thread(new Logs(LocalDateTime.now(), "FavoritoController(actualizarFavorito)", msg)).start();

        return respuesta;
    }

    /**
     * Controlador encargado de borrar favoritos de la base de datos
     * @param favorito Favorito a borrar de la base de datos
     * @return {@true 202 accepted }
     *         {@false 400 bad request}
     *         {@exception 500 internalServer error}
     */
    @DeleteMapping("/borrar")
    public ResponseEntity<Void> borrarFavorito (@RequestBody BorrarFavoritoRequest favorito, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<Void> respuesta;
        String msg;
        String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

        try {

            Optional<Usuario> usuario = usuarioService.findById(idUsuario);

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            FavoritoPK id = FavoritoPK.builder()
                .usuario(usuario.get())
                .idFavorito(favorito.getIdParada())
                .build();

            if (favoritoService.delete(id)){
                respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
                logger.info("Favorito borrado");
                msg = "Favorito borrado";
            }
            else {
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                logger.info("Error: el favorito introducido no existe");
                msg = "No se puede borrar el favorito";
            }

        } catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al eliminar favorito");
            msg = "Error al eliminar favorito " + ((e.getMessage() != null)?e.getMessage():"");
        }

        
        new Thread(new Logs(LocalDateTime.now(), "FavoritoController(borrarFavorito)", msg)).start();
        
        return respuesta;
    }

    /**
     * Controlador encargado de actualizar favoritos en la base de datos
     * @param id Id del usuario del que se quieren recuperar los favoritos
     * @return {@true 202 accepted y coleccion de favoritos del usuario}
     *         {@false 400 bad request}
     *         {@exception 500 internalServer error}
     */
    @GetMapping("/obtener-favoritos")
    public ResponseEntity<List<FavoritoResponse>> obtenerFavoritos(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<List<FavoritoResponse>> respuesta;

        String msg;
        
        String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

        try {
            if (usuarioService.existsById(idUsuario)){

                List<Favorito> favs = favoritoService.findAllByUser(idUsuario);

                List<FavoritoResponse> favoritos = favs.stream().map( fav -> 
                    FavoritoResponse.builder()
                        .idParada(fav.getId().getIdFavorito())
                        .nombreParada(fav.getNombreParada())
                        .build()
                ).collect(Collectors.toList());

                respuesta = new ResponseEntity<>(favoritos, HttpStatus.ACCEPTED);

                msg = "Obtencion de favoritos de usuarios";

                logger.info("Obtencion de favoritos de usuarios");
                
            }
            else {
                respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                msg = "Usuario introducido no existe";
                logger.error("Usuario introducido no existe");
            }
        } catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            msg = "Error al buscar favoritos " + ((e.getMessage() != null)?e.getMessage():"");
            logger.error("Error al buscar favoritos " + ((e.getMessage() != null)?e.getMessage():""));
        }

        new Thread(new Logs(LocalDateTime.now(), "FavoritoController(obtenerFavoritos)", msg)).start();

        return respuesta;

    }
    
}
