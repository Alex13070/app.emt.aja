package org.dam2.appEmt.login.modelo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Tabla favorito en la base de datos MySQL

CREATE TABLE favorito (
    id_favorito VARCHAR(255) NOT NULL,
    nombre_parada VARCHAR(30),
    id_usuario VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_favorito , id_usuario)
)  ENGINE=INNODB;

ALTER TABLE favorito ADD CONSTRAINT FK5vmlgfa4nyi848sknxb237yjt FOREIGN KEY (id_usuario) REFERENCES usuario (correo);

 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Favorito implements Serializable{

    @EmbeddedId
    @NotNull
    private FavoritoPK id;
    
    @Length(max = 30)
    private String nombreParada;
    

}
