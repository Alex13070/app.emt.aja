package org.dam2.appEmt.login.repositorio;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FavoritoUsuarioRepository extends CrudRepository <FavoritoUsuario, Long> {

    @Query("SELECT fu.favorito FROM FavoritoUsuario fu WHERE fu.usuario.id = ?1")
    List<Favorito> obtenerFavoritos (Long id_usuario);
    
}
