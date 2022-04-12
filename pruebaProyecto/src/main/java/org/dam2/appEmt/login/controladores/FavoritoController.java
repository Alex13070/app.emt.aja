package org.dam2.appEmt.login.controladores;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.servicios.IFavoritoService;
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

@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    
    @Autowired
    private IFavoritoService favoritoService;

    @PostMapping("/insertar")
    public ResponseEntity<Favorito> guardarFavorito(@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.save(favorito)){
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Favorito> actualizarFavorito (@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.update(favorito)){
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Favorito> borrarFavorito (@RequestBody Favorito favorito) {

        ResponseEntity<Favorito> respuesta;

        if (favoritoService.delete(favorito)){
            respuesta = new ResponseEntity<Favorito>(favorito, HttpStatus.ACCEPTED);
        }
        else {
            respuesta = new ResponseEntity<Favorito>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;
    }

    @GetMapping("/obtener-favoritos")
    public ResponseEntity<List<Favorito>> actualizarUsuario(@RequestParam String id) {

        ResponseEntity<List<Favorito>> respuesta;

        List<Favorito> favoritos = favoritoService.obtenerFavoritos(id);

        respuesta = new ResponseEntity<>(favoritos, HttpStatus.ACCEPTED);

        return respuesta;

    }
}
