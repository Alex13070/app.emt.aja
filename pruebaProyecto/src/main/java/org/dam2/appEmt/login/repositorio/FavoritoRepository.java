package org.dam2.appEmt.login.repositorio;

import org.dam2.appEmt.login.modelo.Favorito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface FavoritoRepository extends CrudRepository<Favorito, Long>{
    
    // @Query("SELECT f FROM Favorito f WHERE f.id.usuario.id = ?1")
    // List<Favorito> obtenerFavoritos (Long id_usuario);
}
