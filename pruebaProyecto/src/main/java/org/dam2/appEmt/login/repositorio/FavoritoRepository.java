package org.dam2.appEmt.login.repositorio;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de favoritos.
 */
@Repository 
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoPK>{

    /**
     * Busca los favoritos de un usuario
     * @param id correo del usuario
     * @return coleccion con los favoritos del usuario
     */
    @Query("SELECT f FROM Favorito f WHERE f.id.usuario.id = ?1")
    public List<Favorito> obtenerFavoritosPorUsuario (String id);
    
}
