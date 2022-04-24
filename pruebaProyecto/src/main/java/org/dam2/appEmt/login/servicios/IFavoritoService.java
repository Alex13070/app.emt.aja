package org.dam2.appEmt.login.servicios;

import java.util.List;

import org.dam2.appEmt.login.modelo.Favorito;

public interface IFavoritoService {
    
    boolean save (Favorito favorito);
    boolean update (Favorito favorito);
    boolean delete (Favorito favorito);

    //Pruebas
    List<Favorito> findAll();
}
