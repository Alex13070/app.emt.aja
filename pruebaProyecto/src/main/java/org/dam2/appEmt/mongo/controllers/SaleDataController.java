package org.dam2.appEmt.mongo.controllers;

import java.util.List;

import org.dam2.appEmt.mongo.model.SaleData;
import org.dam2.appEmt.mongo.service.ISaleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controladores de {@link SaleData}
 */
@RestController
@RequestMapping("/mongo")
public class SaleDataController {
    
    /**
     * Inyeccion de dependencias de los microservicios de {@link SaleData}
     */
    @Autowired 
    private ISaleDataService saleDataService;

    /**
     * Para crear logs.
     */
    private Logger logger = LoggerFactory.getLogger(SaleDataController.class);
    
    /**
     * Busca todos los SaleData de la base de datos
     * @return Lista de sale data
     */
    @GetMapping("/find-all")
    public ResponseEntity<List<SaleData>> findAll(){

        ResponseEntity<List<SaleData>> respuesta;
        try {
            List<SaleData> saleData = saleDataService.findAll();
            respuesta = new ResponseEntity<>(saleData, HttpStatus.OK);
        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error fatal: {}", e.getMessage());
        }

        return respuesta;
    }

    /**
     * Borra todos los SaleData
     * @return ResponseEntity
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll(){

        ResponseEntity<Void> respuesta;
        try {
            saleDataService.deleteAll();
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            logger.error("Error fatal: {}", e.getMessage());
        }

        return respuesta;
    }

}
