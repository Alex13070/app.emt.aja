package org.dam2.appEmt.mongo.service;

import java.util.List;

import org.dam2.appEmt.mongo.model.SaleData;
import org.dam2.appEmt.mongo.repository.SaleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de los microservicios de {@link SaleData}
 */
@Service
public class SaleDataServiceImpl implements ISaleDataService {

    /**
     * Repositorio de {@link SaleData}
     */
    @Autowired 
    private SaleDataRepository daoSaleData;

    @Override
    public boolean insert(SaleData saleData) {
        boolean exito;
        try {

            daoSaleData.insert(saleData);
            exito = true;

        } catch (Exception e) {
            exito = false;
        }
        
        return exito;
    }

    @Override
    public List<SaleData> findAll() {
        return daoSaleData.findAll();
    }

    @Override
    public void deleteAll() {
        daoSaleData.deleteAll();
    }


    
}
