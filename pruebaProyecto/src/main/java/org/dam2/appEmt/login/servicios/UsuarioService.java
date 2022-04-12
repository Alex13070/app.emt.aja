package org.dam2.appEmt.login.servicios;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

//import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsuarioService
 */

 @Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository daoUsuario;

    @Override
    public boolean insert(Usuario usuario) {
        boolean exito = false;

        if (!daoUsuario.existsById(usuario.getCorreo())) {
            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean exito = false;

        if (daoUsuario.existsById(usuario.getCorreo())) {

            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return daoUsuario.findById(id);
    }

    /*
    @Override
    public List<Favorito> obtenerFavoritos(String id) {
        List<Favorito> resultado = new ArrayList<>();

        if (daoUsuario.existsById(id)){
            resultado = daoUsuario.obtenerFavoritos(id);
        }

        return resultado;
                
    }
    */
    
}