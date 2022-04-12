package org.dam2.appEmt.login.controladores;

import javax.validation.Valid;

import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
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
    
}
