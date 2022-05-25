package org.dam2.appEmt.login.modelPeticion;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritoResponse implements Serializable {
    private String idParada;

    
    @Length(max = 30)
    private String nombreParada;
}
