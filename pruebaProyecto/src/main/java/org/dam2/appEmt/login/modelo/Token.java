package org.dam2.appEmt.login.modelo;

/**
 * Tabla innecesaria.
 
    CREATE TABLE tokens (
        token VARCHAR(255) NOT NULL,
        fecha_fin DATETIME,
        id_usuario VARCHAR(255),
        PRIMARY KEY (token)
    )  ENGINE=INNODB;

    ALTER TABLE tokens ADD CONSTRAINT UK_jv8yedxwjo2w0eeu8tcnfodp UNIQUE (id_usuario);
    ALTER TABLE tokens ADD CONSTRAINT FKm268xjwoth2j04kk1iqnv4i5l FOREIGN KEY (id_usuario) REFERENCES usuario (correo);

 */

// import java.io.Serializable;
// import java.time.LocalDateTime;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;
// import javax.validation.constraints.Future;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @EqualsAndHashCode(onlyExplicitlyIncluded = true)
// @Builder
// @Entity(name = "tokens")
// public class Token implements Serializable{

//     @Id
//     @EqualsAndHashCode.Include
//     private String token;
    
//     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     @JoinColumn(name = "id_usuario", unique = true)
//     private Usuario usuario;

//     @Future(message = "La fecha del token ha de ser futura")
//     private LocalDateTime fechaFin;
    
// }
