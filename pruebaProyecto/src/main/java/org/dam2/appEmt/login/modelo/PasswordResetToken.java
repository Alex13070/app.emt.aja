package org.dam2.appEmt.login.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Tabla de tokens de reseteo de clave
 * 
 * CREATE TABLE password_reset_token (
 *      id BIGINT NOT NULL, 
 *      expiry_date BIGINT, 
 *      token VARCHAR(255), 
 *      id_usuario VARCHAR(255), 
 *      PRIMARY KEY (id)
 * ) ENGINE=INNODB;
 * 
 * alter table password_reset_token add constraint UK_johu5tq9i7cy1fgyemmlme0p2 unique (id_usuario);
 * alter table password_reset_token add constraint FKno4ngi2ecktio49ytrq5d2cxh foreign key (id_usuario) references usuario (correo);
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario user;

    private String token;

    private Long expiryDate;
}