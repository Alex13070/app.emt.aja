package org.dam2.appEmt.login.repositorio;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface FavoritoRepository extends CrudRepository<Favorito, Long>{
    
    @Query("SELECT f FROM Favorito f WHERE f.usuario.correo = ?1")
    List<Favorito> obtenerFavoritos (String id);
}
