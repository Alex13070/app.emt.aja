package org.dam2.appEmt.login.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabla de roles del servidor.

    CREATE TABLE rol (
        id INTEGER NOT NULL,
        nombre VARCHAR(255),
        PRIMARY KEY (id)
    )  ENGINE=INNODB;

    CREATE TABLE usuario_roles (
        usuario_correo VARCHAR(255) NOT NULL,
        roles_id INTEGER NOT NULL,
        PRIMARY KEY (usuario_correo , roles_id)
    )  ENGINE=INNODB;

    ALTER TABLE rol ADD CONSTRAINT UK_43kr6s7bts1wqfv43f7jd87kp UNIQUE (nombre);
    ALTER TABLE usuario_roles ADD CONSTRAINT FK861wr18rjyh6pmadvor1u36vb FOREIGN KEY (roles_id) REFERENCES rol (id);
    ALTER TABLE usuario_roles ADD CONSTRAINT FKjietxva79ywiuor22svjsd178 FOREIGN KEY (usuario_correo) REFERENCES usuario (correo);
    
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String nombre;
    
}
