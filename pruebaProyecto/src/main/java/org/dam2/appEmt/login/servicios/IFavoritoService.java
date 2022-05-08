package org.dam2.appEmt.login.servicios;

import org.dam2.appEmt.login.modelo.Favorito;
import org.dam2.appEmt.login.modelo.FavoritoPK;

/**
 * Funcionalidades de los microservicios de {@link Favorito}
 */
public interface IFavoritoService {
    
    /**
     * Guarda favoritos en la base de datos en base a un favorito dado. 
     * @param favorito Datos a introducir
     * @return {@true Se ha podido introducir}
     *         {@false No se ha podido introducir}
     */
    boolean save (Favorito favorito);

    /**
     * Actualiza favorito en la base de datos en base a un favorito dado. 
     * @param favorito Datos a actualizar
     * @return {@true Se ha podido actualizar}
     *         {@false No se ha podido actualizar}
     */
    boolean update (Favorito favorito);
    
    /**
     * Borra favoritos de la base de datos en base a la clave primaria de un favorito.
     * @param favorito clave primaria de a borrar
     * @return {@true Se ha podido borrar}
     *         {@false No se ha podido borrar}
     */
    boolean delete (FavoritoPK favorito);

    /**
     * Guarda favoritos en la base de datos en base a un favorito dado. 
     * @param idUsuario Id del usuario que esta logeado
     * @return coleccion de favoritos del usuario.
     */
    Favorito[] findAllByUser(String idUsuario);

}
