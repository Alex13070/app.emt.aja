package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import org.dam2.appEmt.login.modelo.PasswordResetToken;

public interface IPasswordResetTokenService {

    PasswordResetToken save(PasswordResetToken token);

    void deleteAllExpired();

    void deleteByUsername(String username);

    Optional<PasswordResetToken> findByUsername(String username);
    
}
