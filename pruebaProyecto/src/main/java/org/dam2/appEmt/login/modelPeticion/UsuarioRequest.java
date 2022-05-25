package org.dam2.appEmt.login.modelPeticion;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.dam2.appEmt.login.modelo.Sexo;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank
    @NotNull
	@Column(unique = true)
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Formato de correo no valido")
    private String correo;

    @NotBlank
    private String clave;

    @Length(max = 20, message = "Nombre demasiado largo")
    @NotBlank
    private String nombre;

    @Length(max = 30, message = "Apellido demasiado largo")
    @NotBlank
    private String apellidos;

    @Past(message = "La fecha tiene que ser anterior a la actual")
    @NotNull
    private LocalDate fechaNacimiento;
    
    @NotNull
    private Sexo sexo;

}
