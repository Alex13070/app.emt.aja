package org.dam2.appEmt.login.servicios;

import java.util.List;
// import java.lang.StackWalker.Option;
// import java.util.List;
import java.util.Optional;

// import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface IUsuarioService {
    
    boolean insert(Usuario usuario);
    boolean update(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByCorreoAndClave(String correo,String clave);
    boolean existsById(Long id);
   
    // Pruebas
    List<Usuario> findAll();
    //List<Favorito> obtenerFavoritos(String id); 
    //boolean delete(Usuario usuario);    

}
