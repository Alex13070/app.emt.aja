package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.Usuario;

/**
 * Funcionalidades de los microservivios de {@link Usuario}
 */
public interface IUsuarioService {
    
    /**
     * Guarda un usuario en la base de datos.
     * @param usuario datos a introducir en la base de datos.
     * @return {@true insertado correctamente }
     *         {@false el usuario ya existia }
     */
    boolean insert(Usuario usuario);

    /**
     * Actualiza un usuario de la base de datos.
     * @param usuario datos a actualizar en la base de datos.
     * @return {@true actualizado correctamente }
     *         {@false el usuario no existia }
     */
    boolean update(Usuario usuario);


    /**
     * Busca por id
     * @param id id usuario
     * @return Optional con usuario
     */
    Optional<Usuario> findById(String id);
    
    /**
     * Busca si el usuario existe en la base de datos.
     * @param id clave primaria del usuario
     * @return {@true existe }
     *         {@false no existe}
     */
    boolean existsById(String id);

    /**
     * Le pone un rol a un usuario de la base de datos
     * @param correo clave del usuario a introducir
     * @param nombreRol nombre del rol a poner al usuario.
     * @return {@true insertado correctamente }
     *         {@false no se ha podido hacer }
     */
    boolean addRol(String correo, String nombreRol);
    
    /**
     * Mira si las claves coinciden
     * @param clave1 Clave a comparar
     * @param clave2 Clave a comparar
     * @return {@true iguales}
     *         {@false no iguales}
     */
    boolean passwordMatches(String raw, String encoded);

}
