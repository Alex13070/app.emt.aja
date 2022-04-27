package org.dam2.appEmt.login.servicios;

import java.time.LocalDateTime;

import org.dam2.appEmt.login.modelo.Key;
import org.springframework.stereotype.Service;

@Service
public interface IKeyService {

    boolean estaOperativo(String token);
    LocalDateTime fechaFin (String token);
    boolean save(Key key);
    boolean delete (String token);

}
