package org.dam2.appEmt.login.servicios;

//import java.util.List;
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

        if (!daoUsuario.existsById(usuario.getId())) {
            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean exito = false;

        if (daoUsuario.existsById(usuario.getId())) {

            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return daoUsuario.findById(id);
    }

    @Override
    public Optional<Usuario> findByCorreoAndClave(String correo, String clave) {
        
        return daoUsuario.findByCorreoAndClave(correo, clave);
    }

    @Override
    public boolean existsById(Long id) {
        return daoUsuario.existsById(id);
    }

    /*
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) daoUsuario.findAll();
    }
    */
    
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