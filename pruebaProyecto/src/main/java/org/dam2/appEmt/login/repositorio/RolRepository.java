package org.dam2.appEmt.login.repositorio;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    
    Optional<Rol> findByNombre(String nombre);
}
