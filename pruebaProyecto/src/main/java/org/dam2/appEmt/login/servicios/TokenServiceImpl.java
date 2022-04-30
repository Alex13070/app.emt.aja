package org.dam2.appEmt.login.servicios;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dam2.appEmt.login.modelo.Token;
import org.dam2.appEmt.login.repositorio.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private TokenRepository daoKey;

    @Override
    public boolean estaOperativo(String token) {
        boolean operativo = false;
        Optional<Token> key = daoKey.findById(token);

        if (key.isPresent()) {
            LocalDateTime t = key.get().getFechaFin();
            operativo =  t.isBefore(LocalDateTime.now());
        }     

        return operativo;
    }

    @Override
    public LocalDateTime fechaFin(String token) {
        
        LocalDateTime time = LocalDateTime.MIN;
        Optional<Token> key = daoKey.findById(token);

        if (key.isPresent()) {
            time = key.get().getFechaFin();
        } 

        return time;
    }

    @Override
    public Optional<Token> save(Token key) {

        return Optional.ofNullable(daoKey.save(key));
    }

    @Override
    public boolean delete(String token) {
        
        boolean exito = false;

        if (daoKey.existsById(token)) {
            daoKey.deleteById(token);
            exito = true;
        }

        return exito;
    }
    
}
