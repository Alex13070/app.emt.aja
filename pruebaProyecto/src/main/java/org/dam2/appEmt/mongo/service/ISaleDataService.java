package org.dam2.appEmt.mongo.service;

import java.util.List;

import org.dam2.appEmt.mongo.model.SaleData;

/**
 * Funcionalidades de los microservivios de {@link SaleData}
 */
public interface ISaleDataService {
 
    /**
     * Guarda datos la base de datos.
     * @param saleData datos a introducir en la base de datos.
     * @return {@true insertado correctamente }
     *         {@false error al introducir }
     */
    boolean insert(SaleData saleData);

    /**
     * Busca todos los datos
     * @return Datos de venta
     */
    List<SaleData> findAll();

    /**
     * Borra todos los datos
     */
    void deleteAll();

}
