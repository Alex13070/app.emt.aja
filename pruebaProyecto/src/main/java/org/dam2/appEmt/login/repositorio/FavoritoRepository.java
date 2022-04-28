package org.dam2.appEmt.login.repositorio;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoPK>{

    @Query("SELECT f FROM Favorito f WHERE f.id.usuario.id = ?1")
    public Favorito[] obtenerFavoritosPorUsuario (String id);
    
}
