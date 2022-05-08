package org.dam2.appEmt.mongo.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.dam2.appEmt.modeloTimeArrival.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Modelo de datos de la base de datos Mongo. Datos a guardar de las peticiones hechas para ver los datos del usuario.
    {
        "fecha": "date", 
        "sexo": "string",
        "edad": "int", 
        "geometry": {
            "type": "Point",
            "coordinates": [
                "latitud",
                "longitud"
            ]
        }
    }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Document("saleData")
public class SaleData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private String id;
    private String fecha;
    private Integer edad;
    private String sexo;
    //importar el del modelo de time arrival coje geojson
    private Point geometry;

}