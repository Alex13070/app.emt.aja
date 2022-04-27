package org.dam2.appEmt.login.servicios;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;
import org.springframework.stereotype.Service;

@Service
public interface IFavoritoService {
    
    boolean save (Favorito favorito);
    boolean update (Favorito favorito);
    boolean delete (FavoritoPK favorito);
    Favorito[] findAllByUser(Long idUsuario);
}
