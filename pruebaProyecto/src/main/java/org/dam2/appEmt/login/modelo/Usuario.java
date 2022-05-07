package org.dam2.appEmt.login.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Usuario implements Serializable{

    @Id
    @NotBlank
    @NotNull
	@EqualsAndHashCode.Include
	@Column(unique = true)
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Formato de correo no valido")
	private String correo;

    //Entre 8 y 12 caracteres, una mayuscula, una minuscula, un numero y un caracter especial
    //@Length(min = 8, max = 20, message = "La clave debe tener entre 8 y 20 caracteres")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "La clave no coincide con los requisitos")
    @NotBlank
    private String clave;


    @Length(max = 20, message = "Nombre demasiado largo")
    @NotBlank
    private String nombre;

    @Length(max = 30, message = "Apellido demasiado largo")
    @NotBlank
    private String apellidos;

    //@Validated(value = ValidarFecha.class)
    //QUITADO TEMPORALMENTE
    //@Past(message = "La fecha tiene que ser anterior a la actual")
    @NotNull
    private LocalDate fechaNacimiento;
    
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Sexo sexo;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rol> roles;

    public boolean addRol(Rol rol) {
        return roles.add(rol);
    }
    
}
