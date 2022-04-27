package org.dam2.appEmt.login.repositorio;

import java.time.LocalDateTime;

import org.dam2.appEmt.login.modelo.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, String> {

    @Query("SELECT k.fechaFin FROM Key k WHERE k.token = ?1")
    LocalDateTime fechaFin (String token);    

}
