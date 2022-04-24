package org.dam2.appEmt.login.modelo;

import java.io.Serializable;
import java.time.LocalDate;
// import java.util.List;

import javax.persistence.Column;
// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
//import org.springframework.validation.annotation.Validated;

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
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
	@EqualsAndHashCode.Include
	@Column(unique = true)
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Formato de correo no valido")
	private String correo;

    //Entre 8 y 12 caracteres, una mayuscula, una minuscula, un numero y un caracter especial
    @Length(min = 8, max = 12, message = "La clave debe tener entre 8 y 12 caracteres")
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "La clave no coincide con los requisitos")
    private String clave;


    @Length(max = 20, message = "Nombre demasiado largo")
    @NotBlank
    private String nombre;

    @Length(max = 30, message = "Apellido demasiado largo")
    @NotBlank
    private String apellidos;

    //@Validated(value = ValidarFecha.class)
    @Past(message = "La fecha tiene que ser anterior a la actual")
    @NotNull
    private LocalDate fechaNacimiento;
    
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Sexo sexo;

    /*
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="FK_USUARIO")
    private List<Favorito> favoritos;
    */
    
}
