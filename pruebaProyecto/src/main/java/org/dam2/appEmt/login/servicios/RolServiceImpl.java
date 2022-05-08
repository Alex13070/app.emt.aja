package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;
import org.dam2.appEmt.login.repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de los microservicios de {@link Rol}
 */
@Service
public class RolServiceImpl implements IRolService{

    /**
     * Inyeccion de dependencias de repositorio de roles.
     */
    @Autowired
    private RolRepository daoRol;

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return daoRol.findByNombre(nombre);
    }

    @Override
    public boolean saveRol(String nombre) {
        boolean respuesta = false;

        Optional<Rol> rol = daoRol.findByNombre(nombre);
        
        if (rol.isEmpty()) {
            respuesta = true;
            daoRol.save(
                Rol.builder()
                    .nombre(nombre)
                    .build()
            );
        }

        return respuesta;
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return daoRol.findByNombre(nombre).isPresent();
    }
}