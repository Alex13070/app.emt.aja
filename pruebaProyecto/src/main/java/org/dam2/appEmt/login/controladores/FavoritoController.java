package org.dam2.appEmt.login.controladores;

import java.util.Optional;

import org.dam2.appEmt.login.modelPeticion.FavoritoRequest;
import org.dam2.appEmt.login.modelPeticion.PKFavoritoRequest;
import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IFavoritoService;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private IFavoritoService favoritoService;

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
    public ResponseEntity<Favorito> guardarFavorito(@RequestBody FavoritoRequest entity) {

        ResponseEntity<Favorito> respuesta;

        try {

            Optional<Usuario> usuario = usuarioService.findById(entity.getPk().getIdUsuario());

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            Favorito favorito = Favorito.builder()
                .id(
                    FavoritoPK.builder()
                        .usuario(usuario.get())
                        .idFavorito(entity.getPk().getIdParada())
                        .build()
                )
                .nombreParada(entity.getNombre())
                .build();


            if (favoritoService.save(favorito)){
                respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.CREATED);
                logger.info("Favorito insertado");
            }
            else {
                respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
                logger.info("Error: El favorito ya existe");
            }
        } catch (Exception e) {
            respuesta = new ResponseEntity<Favorito>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al insertar favorito");
        }

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
    public ResponseEntity<Favorito> actualizarFavorito (@RequestBody FavoritoRequest entity) {

        ResponseEntity<Favorito> respuesta;

        try {

            Optional<Usuario> usuario = usuarioService.findById(entity.getPk().getIdUsuario());

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            Favorito favorito = Favorito.builder()
                .id(
                    FavoritoPK.builder()
                        .usuario(usuario.get())
                        .idFavorito(entity.getPk().getIdParada())
                        .build()
                )
                .nombreParada(entity.getNombre())
                .build();

            if (favoritoService.update(favorito)){
                respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
                logger.info("Favorito actualizado");
            }
            else {
                respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
                logger.info("Error: El favorito a actualizar no existe");
            }

        } catch (Exception e) {
            respuesta = new ResponseEntity<Favorito>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al insertar favorito");
        }

        return respuesta;
    }

    /**
     * Controlador encargado de borrar favoritos de la base de datos
     * @param favorito Favorito a borrar de la base de datos
     * @return {@true 202 accepted y favorito borrado}
     *         {@false 400 bad request}
     *         {@exception 500 internalServer error}
     */
    @DeleteMapping("/borrar")
    public ResponseEntity<FavoritoPK> borrarFavorito (@RequestBody PKFavoritoRequest entity) {

        ResponseEntity<FavoritoPK> respuesta;

        try {

            Optional<Usuario> usuario = usuarioService.findById(entity.getIdUsuario());

            if (usuario.isEmpty()) {
                throw new Exception("El usuario introducido no existe");
            }

            FavoritoPK id = FavoritoPK.builder()
                .usuario(usuario.get())
                .idFavorito(entity.getIdParada())
                .build();

            if (favoritoService.delete(id)){
                respuesta = new ResponseEntity<FavoritoPK>(id, HttpStatus.ACCEPTED);
                logger.info("Favorito borrado");
            }
            else {
                respuesta = new ResponseEntity<FavoritoPK>(HttpStatus.BAD_REQUEST);
                logger.info("Error: el favorito introducido no existe");
            }

        } catch (Exception e) {
            respuesta = new ResponseEntity<FavoritoPK>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al eliminar favorito");
        }
        
        return respuesta;
    }

    /**
     * Controlador encargado de actualizar favoritos en la base de datos
     * @param id Id del usuario del que se quieren recuperar los favoritos
     * @return {@true 202 accepted y coleccion de favoritos del usuario}
     *         {@false 400 bad request}
     */
    @GetMapping("/obtener-favoritos")
    public ResponseEntity<Favorito[]> obtenerFavoritos(@RequestParam String id) {

        ResponseEntity<Favorito[]> respuesta;

        try {
            if (usuarioService.existsById(id)){
                Favorito[] favoritos = favoritoService.findAllByUser(id);
                respuesta = new ResponseEntity<Favorito[]>(favoritos, HttpStatus.ACCEPTED);
                logger.info("Obtencion de favoritos de usuarios");
            }
            else {
                respuesta = new ResponseEntity<Favorito[]>(HttpStatus.BAD_REQUEST);
                logger.error("Error: El usuario introducido no existe");
            }
        } catch (Exception e) {
            respuesta = new ResponseEntity<Favorito[]>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("Error al buscar favoritos");
        }

        return respuesta;

    }
    
}
