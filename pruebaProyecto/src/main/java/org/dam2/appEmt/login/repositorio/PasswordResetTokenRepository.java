package org.dam2.appEmt.login.repositorio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.dam2.appEmt.login.modelo.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> { 
    
    /**
     * Borrado de tokens en base a una fecha
     * @param date new Date ().getTime()
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken p WHERE p.expiryDate < :date")
    void borrarSegunFecha(@Param("date") Long date);

    /**
     * Busqueda de token en base a un correo
     * @param correo correo
     * @return Entidad 
     */
    @Query("SELECT p FROM PasswordResetToken p WHERE p.user.correo = :correo") 
    Optional<PasswordResetToken> findByUsername(@Param("correo") String correo);

    /**
     * Borrado en base a correo
     * @param correo correo
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken p WHERE p.user.correo = :correo")
    void deleteByUsername(@Param("correo") String correo);
}
