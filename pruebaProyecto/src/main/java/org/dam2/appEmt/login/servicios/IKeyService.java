package org.dam2.appEmt.login.servicios;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dam2.appEmt.login.modelo.Key;

public interface IKeyService {

    boolean estaOperativo(String token);
    LocalDateTime fechaFin (String token);
    Optional<Key> save(Key key);
    boolean delete (String token);

}
