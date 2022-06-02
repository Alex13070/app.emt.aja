package org.dam2.appEmt.login.modelo;

/**
 * Clase donde se guardan los Strings con los nombres de los roles de usuario.
 * Por cuestiones de tiempo, hemos decidido dejarlo asi. En un futuro, hay que 
 * cambiarlo a enumerado.
 */
public class NombreRol {
    
    /**
     * Permisos basicos de usuario
     */
    public final static String ROLE_USER = "ROL_USUARIO";
    
    /**
     * Permisos de administrador
     * 
     * - Acceso al controlador de roles de usuario
     * - Acceso a los controladores de Mongo
     */
    public final static String ROLE_ADMIN = "ROL_ADMIN";

}
