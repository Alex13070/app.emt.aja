package org.dam2.appEmt.login.servicios;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dam2.appEmt.login.modelo.Key;
import org.dam2.appEmt.login.repositorio.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService implements IKeyService {

    @Autowired
    private KeyRepository daoKey;

    @Override
    public boolean estaOperativo(String token) {
        boolean operativo = false;

        if (daoKey.existsById(token)) {
            LocalDateTime t = daoKey.fechaFin(token);
            operativo =  t.isBefore(LocalDateTime.now());
        }     

        return operativo;
    }

    @Override
    public LocalDateTime fechaFin(String token) {
        
        LocalDateTime time = LocalDateTime.MIN;

        if (daoKey.existsById(token)) {
            time = daoKey.fechaFin(token);
        }

        return time;
    }

    @Override
    public Optional<Key> save(Key key) {

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
