package org.dam2.appEmt.login.servicios;

import java.time.LocalDateTime;

import org.dam2.appEmt.login.modelo.Key;
import org.dam2.appEmt.login.repositorio.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class KeyService implements IKeyService {

    @Autowired
    private KeyRepository daoKey;

    @Override
    public boolean estaOperativo(String token) {
        

        

        return false;
    }

    @Override
    public LocalDateTime fechaFin(String token) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Key key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(String token) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
