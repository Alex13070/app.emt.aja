package org.dam2.appEmt.login.servicios;

import java.util.Date;
import java.util.Optional;

import org.dam2.appEmt.configuration.cifrado.MD5;
import org.dam2.appEmt.login.modelo.PasswordResetToken;
import org.dam2.appEmt.login.repositorio.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements IPasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository daoPasswordResetToken;

    @Override
    public PasswordResetToken save(PasswordResetToken token) {
        token.setToken(MD5.encriptar(token.getToken()));
        return daoPasswordResetToken.save(token);
    }

    @Override
    public void deleteAllExpired() {
        daoPasswordResetToken.borrarSegunFecha(new Date().getTime());
    }

    @Override
    public Optional<PasswordResetToken> findByUsername(String username) {
        return daoPasswordResetToken.findByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        daoPasswordResetToken.deleteByUsername(username);;
    }

    
    
}
