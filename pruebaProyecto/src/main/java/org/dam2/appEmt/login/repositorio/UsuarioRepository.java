package org.dam2.appEmt.login.repositorio;

import org.dam2.appEmt.login.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}
