package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;
import org.dam2.appEmt.login.repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private RolRepository daoRol;

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return daoRol.findByNombre(nombre);
    }

    @Override
    public Rol saveRol(String nombre) {
        Rol respuesta = null;

        Optional<Rol> rol = daoRol.findByNombre(nombre);
        
        if (rol.isEmpty()) {
            respuesta = Rol.builder().nombre(nombre).build();
            daoRol.save(respuesta);
        }

        return respuesta;
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return daoRol.findByNombre(nombre).isPresent();
    }
}