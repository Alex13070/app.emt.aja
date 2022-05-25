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
    
    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken p WHERE p.expiryDate < :date")
    void borrarSegunFecha(@Param("date") Long date);

    @Query("SELECT p FROM PasswordResetToken p WHERE p.user.correo = :correo") 
    Optional<PasswordResetToken> findByUsername(@Param("correo") String correo);

    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken p WHERE p.user.correo = :correo")
    void deleteByUsername(@Param("correo") String correo);
}
