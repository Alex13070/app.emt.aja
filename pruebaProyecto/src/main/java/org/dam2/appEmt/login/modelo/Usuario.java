package org.dam2.appEmt.login.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Usuario implements Serializable{

    @NonNull
	@EqualsAndHashCode.Include
	@Id
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Formato de correo no valido")
	private String correo;

    /*

        <b> Condidiones de la clave </b>

        ^ represents starting character of the string.
        (?=.*[0-9]) represents a digit must occur at least once.
        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
        (?=\\S+$) white spaces don’t allowed in the entire string.
        .{8, 20} represents at least 8 characters and at most 20 characters.
        $ represents the end of the string.
    */
    @Min(value = 8, message = "Demasiado corta")
    @Max(value = 12, message = "Demasiado larga")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$", message = "Condiciones de clave") 
    private String clave;

    @Length(max = 20)
    private String nombre;

    @Length(max = 30)
    private String apellidos;

    //@Validated(value = ValidarFecha.class)
    private LocalDate fechaNacimiento;
    
    @Enumerated(value = EnumType.STRING)
    private Sexo sexo;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="FK_USUARIO")
    private List<Favorito> favoritos;
    
}
