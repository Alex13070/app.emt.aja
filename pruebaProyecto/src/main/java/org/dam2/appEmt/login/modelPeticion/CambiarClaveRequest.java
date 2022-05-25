package org.dam2.appEmt.login.modelPeticion;

import lombok.Data;

@Data
public class CambiarClaveRequest {

    private String idUsuario;

    private String clave;

    private String token;
    
}
