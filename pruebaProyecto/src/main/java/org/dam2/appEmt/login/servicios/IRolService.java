package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;

public interface IRolService {

    Optional<Rol> findByNombre(String nombre);
    Rol saveRol(String nombre);
    boolean existsByNombre (String nombre);

}
