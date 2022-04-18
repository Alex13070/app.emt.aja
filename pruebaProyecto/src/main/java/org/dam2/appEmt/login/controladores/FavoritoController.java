package org.dam2.appEmt.login.controladores;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
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
     * @return {@true 202 accepted y favorito introducido}
     *         {@false 400 bad request}
     */
    @PostMapping("/insertar")
    public ResponseEntity<Favorito> guardarFavorito(@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.save(favorito)){
            logger.info("Favorito insertado");
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            logger.error("Error al insertar favorito");
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;
    }

    /**
     * Controlador encargado de actualizar favoritos en la base de datos
     * @param favorito Favorito a introducir en la base de datos
     * @return {@true 202 accepted y favorito actualizado}
     *         {@false 400 bad request}
     */
    @PutMapping("/actualizar")
    public ResponseEntity<Favorito> actualizarFavorito (@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.update(favorito)){
            logger.info("Favorito actualizado");
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            logger.info("Error al actualizar favorito");
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;
    }

    /**
     * Controlador encargado de borrar favoritos de la base de datos
     * @param favorito Favorito a borrar de la base de datos
     * @return {@true 202 accepted y favorito borrado}
     *         {@false 400 bad request}
     */
    @DeleteMapping("/borrar")
    public ResponseEntity<Favorito> borrarFavorito (@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.delete(favorito)){
            logger.info("Favorito borrado");
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            logger.error("Error al borrar favorito");
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<List<Favorito>> actualizarUsuario(@RequestParam String id) {

        ResponseEntity<List<Favorito>> respuesta;

        if (usuarioService.existsById(id)){
            logger.info("Obtencion de favoritos de usuarios");
            List<Favorito> favoritos = favoritoService.obtenerFavoritos(id);
            respuesta = new ResponseEntity<>(favoritos, HttpStatus.ACCEPTED);
        }
        else {
            logger.error("Error al conseguir favoritos de usuario");
            respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;

    }
}
