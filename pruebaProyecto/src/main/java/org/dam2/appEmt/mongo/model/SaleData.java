package org.dam2.appEmt.mongo.model;

import java.io.Serializable;


import org.dam2.appEmt.modeloTimeArrival.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("saleData")
public class SaleData implements Serializable {

    @Id
    private long id;
    private String fecha;
    private Integer edad;
    private String sexo;
    //importar el del modelo de time arrival coje geojson
    private Point geometry;

}