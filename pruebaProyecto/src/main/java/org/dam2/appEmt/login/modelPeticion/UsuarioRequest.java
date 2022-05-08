package org.dam2.appEmt.login.modelPeticion;

import java.io.Serializable;
import java.time.LocalDate;

import org.dam2.appEmt.login.modelo.Sexo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Peticion con todos los datos del usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest implements Serializable {

    private String correo;

    private String clave;

    private String nombre;

    private String apellidos;

    private LocalDate fechaNacimiento;
    
    private Sexo sexo;

}
