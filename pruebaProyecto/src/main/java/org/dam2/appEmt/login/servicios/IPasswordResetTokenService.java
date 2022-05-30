package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.PasswordResetToken;

/**
 * Microservicios de token de reseteo de contraseña
 */
public interface IPasswordResetTokenService {

    /**
     * Guarda/Actualiza un nuevo token de reseteo
     * @param token token a guardar/actualizar
     * @return token guardado
     */
    PasswordResetToken save(PasswordResetToken token);

    /**
     * Borra los tokens expirados
     */
    void deleteAllExpired();

    /**
     * Borra el token referente a un usuario
     * @param username
     */
    void deleteByUsername(String username);

    /**
     * Busca token en base a un correo
     * @param username correo
     * @return Entidad con token
     */
    Optional<PasswordResetToken> findByUsername(String username);
    
}
