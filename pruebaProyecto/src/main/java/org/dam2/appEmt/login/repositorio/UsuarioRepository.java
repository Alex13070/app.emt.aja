package org.dam2.appEmt.login.repositorio;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoAndClave(String correo,String clave);
    Optional<Usuario> findByCorreo(String correo);
}
