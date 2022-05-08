package org.dam2.appEmt.mongo.repository;

import org.dam2.appEmt.mongo.model.SaleData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de {@link SaleData}} 
 */
@Repository
public interface SaleDataRepository extends MongoRepository<SaleData, String> {
    
}
