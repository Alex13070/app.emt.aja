package org.dam2.appEmt.login.servicios;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.repositorio.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService implements IFavoritoService{

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
    public boolean delete(Favorito fav) {
        boolean exito = false;
        
        if (daoFavorito.existsById(fav.getId())){
            daoFavorito.delete(fav);
            exito = true;
        } 
        
        return exito;
    }

    @Override
    public List<Favorito> findAll() {
        return (List<Favorito>) daoFavorito.findAll();
    }

    
    
}
