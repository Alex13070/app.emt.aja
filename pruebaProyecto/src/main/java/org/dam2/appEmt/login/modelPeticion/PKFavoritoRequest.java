package org.dam2.appEmt.login.modelPeticion;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Peticion de claves primarias de los favoritos de los usuarios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PKFavoritoRequest implements Serializable {

    private String idUsuario;

    private String idParada;
}
