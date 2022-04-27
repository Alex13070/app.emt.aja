package org.dam2.appEmt.login.modelPeticion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PKFavoritoRequest {

    private Long idUsuario;

    private String idParada;
}
