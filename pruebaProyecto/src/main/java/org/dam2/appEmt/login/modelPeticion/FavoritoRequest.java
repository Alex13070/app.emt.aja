package org.dam2.appEmt.login.modelPeticion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritoRequest {

    private PKFavoritoRequest pk;

    private String nombre;
    
}
