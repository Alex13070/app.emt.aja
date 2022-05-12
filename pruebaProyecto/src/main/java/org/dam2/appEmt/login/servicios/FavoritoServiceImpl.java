package org.dam2.appEmt.login.servicios;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.dam2.appEmt.login.repositorio.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de los microservicios de {@link Favorito}
 */
@Service
public class FavoritoServiceImpl implements IFavoritoService{

    /**
     * Inyeccion de dependencias de repositorio de usuarios.
     */
    @Autowired
    private FavoritoRepository daoFavorito;

    @Override
    public boolean save(Favorito fav) {
        boolean exito = false;
        
        if (!daoFavorito.existsById(fav.getId())){

            daoFavorito.save(fav);
            exito = true;
        } 

        return exito;
    }

    @Override
    public boolean update(Favorito fav) {
        boolean exito = false;
        
        if (daoFavorito.existsById(fav.getId())){
            daoFavorito.save(fav);
            exito = true;
        } 

        return exito;
    }

    @Override
    public boolean delete(FavoritoPK fav) {
        boolean exito = false;
        
        if (daoFavorito.existsById(fav)){
            daoFavorito.deleteById(fav);
            exito = true;
        } 
        
        return exito;
    }

    @Override
    public List<Favorito> findAllByUser(String id) {
        return daoFavorito.obtenerFavoritosPorUsuario(id);
    }

    
    
}
