package org.dam2.appEmt.mongo.controllers;

// import org.dam2.appEmt.mongo.model.SaleData;
// import org.dam2.appEmt.mongo.service.ISaleDataService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// @RestController
// @RequestMapping("/mongo")
// public class SaleDataController {
    
//     @Autowired 
//     private ISaleDataService saleDataService;

//     /**
//      * Para crear logs.
//      */
//     private Logger logger = LoggerFactory.getLogger(SaleDataController.class);
    
//     @PostMapping("insertar")
//     public ResponseEntity<SaleData> insert(@RequestBody SaleData saleData ){

//         ResponseEntity<SaleData> respuesta;
//         try {
//             //FIXME: tenemos que hablarlo
//             if (saleDataService.insert(saleData)!=null) {
//                 respuesta = new ResponseEntity<>(saleData, HttpStatus.CREATED);
//                 logger.info("Dato de venta insertado");
//             }
//             else {
//                 logger.info("Error: El el dato de venta no insertado");
//                 respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);   
//             }

//         }
//         catch (Exception e) {
//             respuesta = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//             logger.error("Error fatal");
//         }

//         return respuesta;
//     }

// }
