package org.dam2.appEmt.login.repositorio;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de usuarios
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    /**
     * Busca si existe un usuario en la base de datos en base al correo que este tiene.
     * @param correo id del usuario
     * @return Objeto de la clase {@link Optional} que ira con datos en caso de que 
     *         exista en la base de datos o ira vacio en caso contrario
     */
    Optional<Usuario> findByCorreo(String correo);
    
}
