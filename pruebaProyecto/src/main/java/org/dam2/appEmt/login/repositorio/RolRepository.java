package org.dam2.appEmt.login.repositorio;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de roles
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    
    /**
     * Busca un rol en base a su nombre
     * @param nombre Nombre del rol
     * @return Objeto de la clase {@link Optional} que ira con datos en caso de que 
     *         exista en la base de datos o ira vacio en caso contrario
     */
    Optional<Rol> findByNombre(String nombre);
}
