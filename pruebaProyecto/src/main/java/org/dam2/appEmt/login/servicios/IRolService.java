package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;

/**
 * Funcionalidades de los microservicios de los {@link Rol}
 */
public interface IRolService {

    /**
     * Busca un rol en base a su nombre.
     * @param nombre nombre del rol
     * @return devuelve un {@link Optional} en el que habra datos en caso de que el rol exista e ira vacio en caso contrario.
     */
    Optional<Rol> findByNombre(String nombre);

    /**
     * Guarda roles en la base de datos en base al nombre introducido
     * @param nombre nombre del rol a guardar
     * @return {@true Se ha podido guardar}
     *         {@false No se ha podido guardar}
     */
    boolean saveRol(String nombre);

    /**
     * Busca en la base de datos si el rol introducido existe.
     * @param nombre nombre del rol a buscar
     * @return {@true Existe}
     *         {@false No existe}
     */
    boolean existsByNombre (String nombre);

}
