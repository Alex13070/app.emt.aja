package org.dam2.appEmt.login.modelPeticion;

import java.io.Serializable;

import lombok.Data;

/**
 * Request para poner roles en usuarios
 */
@Data
public class AddRolRequest implements Serializable {
    
    private String correo;
    private String rol;
}
